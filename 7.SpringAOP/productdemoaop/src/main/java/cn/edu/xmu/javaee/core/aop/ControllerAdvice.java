//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.aop;

import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 处理RestController的validation Exception
 */
@RestControllerAdvice
public class ControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * 需要配置
     * server.servlet.encoding.charset=UTF-8
     * server.servlet.encoding.enabled=true
     * server.servlet.encoding.force=true
     * 否则中文返回是乱码
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ReturnObject bindExceptionHandler(Exception e){
        StringBuilder sb = new StringBuilder();

        if (e instanceof BindException) {
            List<FieldError> allErrors = ((BindException) e).getFieldErrors();
            for (FieldError error : allErrors) {
                sb.append(String.format("%s:%s,", error.getField(), error.getDefaultMessage()));
            }
        }else if (e instanceof  MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            FieldError error = bindingResult.getFieldError();
            sb.append(error.getDefaultMessage());
        }else{
            sb.append(e.getMessage());
        }
        logger.debug("bindExceptionHandler: e = {}",sb.toString());
        return new ReturnObject(ReturnNo.FIELD_NOTVALID, sb.toString());
    }
}
