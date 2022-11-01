//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.controller;

import cn.edu.xmu.javaee.core.aop.Audit;
import cn.edu.xmu.javaee.core.aop.LoginName;
import cn.edu.xmu.javaee.core.aop.LoginUser;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.core.model.SimpleUser;
import cn.edu.xmu.oomall.payment.controller.vo.OrderPayVo;
import cn.edu.xmu.oomall.payment.controller.vo.PayTransRetVo;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.edu.xmu.javaee.core.util.Common.*;

/**
 * 内部的接口
 */
@RestController
@RequestMapping(value = "/internal", produces = "application/json;charset=UTF-8")
public class InternalPaymentController {

    private final Logger logger = LoggerFactory.getLogger(InternalPaymentController.class);

    private PaymentService paymentService;

    @Autowired
    public InternalPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("orders/{id}/payments")
    @Audit(departName = "shops")
    public ReturnObject createPayment(@Validated @RequestBody OrderPayVo orderPayVo, @LoginUser SimpleUser user){
        PayTrans payTrans =  paymentService.createPayment(orderPayVo.getSpOpenid(),
                orderPayVo.getBusinessId(), orderPayVo.getShopChannelId(), orderPayVo.getAmount(), user);
        PayTransRetVo vo = cloneObj(payTrans, PayTransRetVo.class);

        clearFields(vo, "id", "prepayId");
        return new ReturnObject(vo);
    }

}
