package cn.edu.xmu.javaee.core.aop;

import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.goodsdemo.controller.AdminProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import static cn.edu.xmu.javaee.core.util.ResponseUtil.retWithObj;

/**
 * 处理控制器错误
 * @author Ming Qiu
 **/
@RestControllerAdvice(basePackageClasses = AdminProductController.class)
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    private HttpServletResponse httpServletResponse;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValid(MethodArgumentNotValidException exception){

        StringBuffer msg = new StringBuffer();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.append(error.getDefaultMessage());
            msg.append(";");
        }
        logger.info("methodArgumentNotValid: msg = "+ msg.toString());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return retWithObj(ReturnNo.FIELD_NOTVALID, msg.toString(), null);
    }
}
