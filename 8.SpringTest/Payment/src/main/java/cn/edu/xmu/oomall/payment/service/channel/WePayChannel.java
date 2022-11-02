//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service.channel;

import cn.edu.xmu.oomall.payment.dao.ShopChannelDao;
import cn.edu.xmu.oomall.payment.dao.bo.Channel;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.dao.bo.ShopChannel;
import cn.edu.xmu.oomall.payment.service.PaymentService;
import cn.edu.xmu.oomall.payment.service.openfeign.WePayService;
import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransParam;
import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransRetObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信支付
 */
@Service("wePayChannel")
public class WePayChannel implements PayChannel{

    private Logger logger = LoggerFactory.getLogger(WePayChannel.class);

    private WePayService wePayService;

    @Autowired
    public WePayChannel(WePayService wePayService) {
        this.wePayService = wePayService;
    }

    @Override
    public void createPayment(PayTrans payTrans) {
        PostTransParam param = new PostTransParam();
        PostTransParam.Amount amount1 = param.new Amount();
        amount1.setTotal(payTrans.getAmount());
        amount1.setCurrency("CNY");
        param.setAmount(amount1);

        PostTransParam.Payer payer = param.new Payer();
        payer.setSp_openid(payTrans.getSpOpenid());
        param.setPayer(payer);

        ShopChannel shop = payTrans.getShop();

        param.setDescription("随便写啥");
        Channel channel = shop.getChannel();

        param.setSp_appid(channel.getAppid());
        param.setNotify_url(channel.getNotifyUrl());
        param.setSp_mchid(channel.getSpMchid());
        param.setSub_mchid(shop.getSubMchid());
        param.setOut_trade_no(payTrans.getOutNo());

        PostTransRetObj retObj = wePayService.postTransaction(param);
        payTrans.setPrepayId(retObj.getPrepayId());
    }
}
