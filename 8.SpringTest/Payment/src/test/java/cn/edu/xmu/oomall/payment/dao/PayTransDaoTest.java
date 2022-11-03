//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.PaymentApplication;
import cn.edu.xmu.oomall.payment.dao.PayTransDao;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaymentApplication.class)
@Transactional
public class PayTransDaoTest {

    @Autowired
    private PayTransDao payTransDao;

    @Test
    public void updateObjById1(){

        SimpleUser user = new SimpleUser();
        user.setId(Long.valueOf(2));
        user.setName("test1");
        user.setUserLevel(1);
        PayTrans obj = new PayTrans();
        obj.setId(Long.valueOf(1));
        obj.setOutNo("aaaa");

        assertThrows(BusinessException.class, () ->payTransDao.updateObjById(obj, user));

    }
}
