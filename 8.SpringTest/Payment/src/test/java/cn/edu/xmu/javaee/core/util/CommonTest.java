//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.javaee.core.util;

import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.dao.bo.Business;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.dao.bo.RefundTrans;
import cn.edu.xmu.oomall.payment.mapper.generator.po.BusinessPo;
import cn.edu.xmu.oomall.payment.mapper.generator.po.PayTransPo;
import cn.edu.xmu.oomall.payment.mapper.generator.po.RefundTransPo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static cn.edu.xmu.javaee.core.util.Common.*;


public class CommonTest {

    @Test
    public void cloneObj1(){
        LocalDateTime now = LocalDateTime.now();
        BusinessPo po = new BusinessPo();
        po.setId(Long.valueOf(1));
        po.setName("测试");
        po.setCreatorId(Long.valueOf(2));
        po.setCreatorName("管理员");
        po.setGmtCreate(now);
        po.setGmtModified(now.minusDays(Long.valueOf(1)));

        Business bo = cloneObj(po, Business.class);
        assertEquals(Long.valueOf(1), bo.getId());
        assertEquals("测试", bo.getName());
        assertEquals(Long.valueOf(2), bo.getCreatorId());
        assertEquals("管理员",bo.getCreatorName());
        assertEquals(now, bo.getGmtCreate());
        assertEquals(now.minusDays(Long.valueOf(1)), bo.getGmtModified());
    }

    @Test
    public void cloneObj2(){
        LocalDateTime now = LocalDateTime.now();
        RefundTransPo po = new RefundTransPo();
        po.setId(Long.valueOf(1));
        po.setOutNo("Ref001");
        po.setTransNo("001");
        po.setAmount(Long.valueOf(100));
        po.setSuccessTime(now);
        po.setBusinessId(Long.valueOf(0));
        po.setAdjustId(Long.valueOf(3));
        po.setAdjustName("调账员");
        po.setAdjustTime(now.plusDays(Long.valueOf(1)));
        po.setCreatorId(Long.valueOf(2));
        po.setCreatorName("管理员");
        po.setGmtCreate(now);
        po.setGmtModified(now.minusDays(Long.valueOf(1)));

        RefundTrans bo = cloneObj(po, RefundTrans.class);
        assertEquals(Long.valueOf(1), bo.getId());
        assertEquals("Ref001", bo.getOutNo());
        assertEquals(Long.valueOf(2), bo.getCreatorId());
        assertEquals("管理员",bo.getCreatorName());
        assertEquals(now, bo.getGmtCreate());
        assertEquals(now.minusDays(Long.valueOf(1)), bo.getGmtModified());
        assertEquals("001", bo.getTransNo());
        assertEquals(Long.valueOf(100), bo.getAmount());
        assertEquals(now, bo.getSuccessTime());
        assertNull(bo.getBusiness());
        assertNull(bo.getShop());
        assertEquals(Long.valueOf(3), bo.getAdjustId());
        assertEquals(now.plusDays(Long.valueOf(1)), bo.getAdjustTime());
    }

    @Test
    public void clearFields1(){
        LocalDateTime now = LocalDateTime.now();
        RefundTransPo po = new RefundTransPo();
        po.setId(Long.valueOf(1));
        po.setOutNo("Ref001");
        po.setTransNo("001");
        po.setAmount(Long.valueOf(100));
        po.setSuccessTime(now);
        po.setBusinessId(Long.valueOf(0));
        po.setAdjustId(Long.valueOf(3));
        po.setAdjustName("调账员");
        po.setAdjustTime(now.plusDays(Long.valueOf(1)));
        po.setCreatorId(Long.valueOf(2));
        po.setCreatorName("管理员");
        po.setGmtCreate(now);
        po.setGmtModified(now.minusDays(Long.valueOf(1)));


        boolean ret = clearFields(po, "id","outNo","transNo");
        assertTrue(ret);
        assertEquals(Long.valueOf(1), po.getId());
        assertEquals("Ref001", po.getOutNo());
        assertNull(po.getCreatorId());
        assertNull(po.getCreatorName());
        assertNull(po.getGmtCreate());
        assertNull(po.getGmtModified());
        assertEquals("001", po.getTransNo());
        assertNull(po.getAmount());
        assertNull(po.getSuccessTime());
        assertNull(po.getAdjustId());
        assertNull(po.getAdjustTime());
    }

    @Test
    public void putUserFields1(){
        RefundTransPo po = new RefundTransPo();
        SimpleUser user = new SimpleUser();
        user.setId(Long.valueOf(2));
        user.setName("test1");
        user.setUserLevel(1);
        putUserFields(po, "creator", user);
        assertEquals(2, po.getCreatorId());
        assertEquals("test1", po.getCreatorName());
    }

    @Test
    public void putUserFields2(){
        PayTrans bo = new PayTrans();
        SimpleUser user = new SimpleUser();
        user.setId(Long.valueOf(2));
        user.setName("test1");
        user.setUserLevel(1);
        putUserFields(bo, "modifier", user);
        System.out.println(bo);
        assertEquals(2, bo.getModifierId());
        assertEquals("test1", bo.getModifierName());
        assertNull(bo.getCreatorId());
        assertNull(bo.getCreatorName());
        assertNull(bo.getAdjustId());
        assertNull(bo.getAdjustName());
    }

    @Test
    public  void putGmtFields1(){
        PayTransPo po = new PayTransPo();
        LocalDateTime now = LocalDateTime.now();
        putGmtFields(po, "create");
        assertThat(po.getGmtCreate()).isAfterOrEqualTo(now);
        assertThat(po.getGmtModified()).isNull();
    }
}
