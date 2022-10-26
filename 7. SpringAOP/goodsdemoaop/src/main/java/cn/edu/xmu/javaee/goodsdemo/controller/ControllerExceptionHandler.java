//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller;

import cn.edu.xmu.javaee.core.util.ReturnNo;
import cn.edu.xmu.javaee.core.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理控制器错误
 * @author Ming Qiu
 **/
@RestControllerAdvice(basePackages = {"cn.edu.xmu.javaee.goodsdemo.controller"})
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletResponse response){

        StringBuffer msg = new StringBuffer();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.append(error.getDefaultMessage());
            msg.append(";");
        }
        logger.debug("methodArgumentNotValid: msg = {}", msg.toString());
        response.setContentType("application/json;charset=UTF-8");
        ReturnObject retObj = new ReturnObject(ReturnNo.FIELD_NOTVALID, msg.toString());
        return new ResponseEntity(retObj, HttpStatus.BAD_REQUEST);
    }

}
