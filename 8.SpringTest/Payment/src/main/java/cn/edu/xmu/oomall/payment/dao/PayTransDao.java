//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.mapper.generator.PayTransPoMapper;
import cn.edu.xmu.oomall.payment.mapper.generator.po.PayTransPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static cn.edu.xmu.javaee.core.util.Common.*;

@Repository
public class PayTransDao {

    private Logger logger = LoggerFactory.getLogger(PayTransDao.class);

    private ShopChannelDao shopChannelDao;

    private PayTransPoMapper payTransPoMapper;

    public void insertObj(PayTrans obj, SimpleUser user) throws RuntimeException{
        PayTransPo po = cloneObj(obj, PayTransPo.class);
        putUserFields(po, "creator", user);
        putGmtFields(po, "create");
        payTransPoMapper.insert(po);
        obj.setId(po.getId());
        obj.setShopChannelDao(this.shopChannelDao);

    }

    public void updateObjById(PayTrans obj, SimpleUser user) throws RuntimeException{
        PayTransPo po = cloneObj(obj, PayTransPo.class);
        if (null != user) {
            putUserFields(po, "modifier", user);
            putGmtFields(po, "Modified");
        }
        payTransPoMapper.updateByPrimaryKey(po);
    }
}
