package cn.edu.xmu.restfuldemo.controller;

import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理控制器错误
 * @author Ming Qiu
 **/
@RestControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    private HttpServletResponse httpServletResponse;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object methodArgumentNotValid(MethodArgumentNotValidException exception){

        StringBuffer msg = new StringBuffer();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.append(error.getDefaultMessage());
            msg.append(";");
        }
        logger.info("methodArgumentNotValid: msg = "+ msg.toString());
        ReturnObject retObj = new ReturnObject(ResponseCode.FIELD_NOTVALID, msg.toString());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        return retObj;
    }
}
