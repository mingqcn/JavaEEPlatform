//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service;

import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.javaee.core.util.RedisUtil;
import cn.edu.xmu.oomall.payment.PaymentApplication;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.service.PaymentService;
import cn.edu.xmu.oomall.payment.service.openfeign.WePayService;
import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransRetObj;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PaymentApplication.class)
@Transactional
public class PayServiceTest {
    @MockBean
    private RedisUtil redisUtil;

    @MockBean
    private WePayService wePayService;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void createPayment(){
        PostTransRetObj retObj = new PostTransRetObj();
        retObj.setPrepayId("2311111");

        SimpleUser user = new SimpleUser();
        user.setId(Long.valueOf(2));
        user.setName("test1");
        user.setUserLevel(1);

        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        Mockito.when(wePayService.postTransaction(Mockito.any())).thenReturn(retObj);

        PayTrans obj = paymentService.createPayment("1122", "11111", Long.valueOf(501), Long.valueOf(501), Long.valueOf(100), user);
        assertEquals("2311111", obj.getPrepayId());

    }
}
