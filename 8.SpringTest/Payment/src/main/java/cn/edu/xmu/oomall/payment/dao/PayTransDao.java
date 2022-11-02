//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.mapper.generator.PayTransPoMapper;
import cn.edu.xmu.oomall.payment.mapper.generator.po.PayTransPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static cn.edu.xmu.javaee.core.util.Common.*;

@Repository
public class PayTransDao {

    private Logger logger = LoggerFactory.getLogger(PayTransDao.class);

    private ShopChannelDao shopChannelDao;

    private PayTransPoMapper payTransPoMapper;

    @Autowired
    public PayTransDao(ShopChannelDao shopChannelDao, PayTransPoMapper payTransPoMapper) {
        this.shopChannelDao = shopChannelDao;
        this.payTransPoMapper = payTransPoMapper;
    }

    public void insertObj(PayTrans obj, SimpleUser user) throws RuntimeException{
        logger.debug("insertObj: obj = {}", obj);
        PayTransPo po = cloneObj(obj, PayTransPo.class);
        putUserFields(po, "creator", user);
        putGmtFields(po, "create");
        logger.debug("insertObj: po = {}", po);
        payTransPoMapper.insertSelective(po);
        obj.setId(po.getId());
        obj.setShopChannelDao(this.shopChannelDao);

    }

    public void updateObjById(PayTrans obj, SimpleUser user) throws RuntimeException{
        PayTransPo po = cloneObj(obj, PayTransPo.class);
        if (null != user) {
            putUserFields(po, "modifier", user);
            putGmtFields(po, "Modified");
        }
        int ret = payTransPoMapper.updateByPrimaryKeySelective(po);
        if (0 == ret){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "支付交易",po.getId()));
        }
    }
}
