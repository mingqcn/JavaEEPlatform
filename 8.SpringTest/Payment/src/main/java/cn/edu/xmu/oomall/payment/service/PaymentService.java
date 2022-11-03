//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.dao.PayTransDao;
import cn.edu.xmu.oomall.payment.dao.ShopChannelDao;
import cn.edu.xmu.oomall.payment.dao.bo.Channel;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.dao.bo.ShopChannel;
import cn.edu.xmu.oomall.payment.service.channel.PayChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import static cn.edu.xmu.javaee.core.util.Common.clearFields;

/**
 * 支付的服务
 */
@Service
public class PaymentService {

    private Logger logger = LoggerFactory.getLogger(ShopChannelDao.class);

    private ApplicationContext context;

    private ShopChannelDao shopChannelDao;

    private PayTransDao payTransDao;


    @Autowired
    @Lazy
    public PaymentService(ApplicationContext context, ShopChannelDao shopChannelDao, PayTransDao payTransDao) {
        this.logger = logger;
        this.context = context;
        this.shopChannelDao = shopChannelDao;
        this.payTransDao = payTransDao;
    }

    /**
     * 创建一个支付交易
     * @author Ming Qiu
     * <p>
     * date: 2022-11-01 19:18
     * @param outNo 内部交易号
     * @param spOpenid 用户的支付id
     * @param businessId 业务Id
     * @param shopChannelId 商铺渠道Id
     * @param amount 支付金额
     * @param user 当前登录用户
     * @return 支付交易对象
     */
    public PayTrans createPayment(String outNo, String spOpenid, Long businessId, Long shopChannelId, Long amount, SimpleUser user) throws BusinessException {
        ShopChannel shop = this.shopChannelDao.findObjById(shopChannelId);
        logger.debug("createPayment: shop = {}", shop);
        if (ShopChannel.INVALID == shop.getStatus()){
            throw new BusinessException(ReturnNo.PAY_SHOP_INVALID);
        }

        Channel channel = shop.getChannel();
        logger.debug("createPayment: channel = {}", channel);
        if (Channel.INVALID == channel.getStatus()){
            throw new BusinessException(ReturnNo.PAY_CHANNEL_INVALID);
        }

        PayTrans newObj = new PayTrans();
        newObj.setOutNo(outNo);
        newObj.setSpOpenid(spOpenid);
        newObj.setBusinessId(businessId);
        newObj.setShopChannelId(shopChannelId);
        newObj.setAmount(amount);
        payTransDao.insertObj(newObj, user);

        PayChannel payChannel = this.retPayChannel(shop);
        logger.debug("createPayment: payChannel = {}", payChannel);
        payChannel.createPayment(newObj);
        clearFields(newObj,"id","prepayId");
        payTransDao.updateObjById(newObj, null);
        return newObj;
    }

    private PayChannel retPayChannel(ShopChannel shop){
        Channel channel = shop.getChannel();
        return (PayChannel) context.getBean(channel.getBeanName());
    }
}
