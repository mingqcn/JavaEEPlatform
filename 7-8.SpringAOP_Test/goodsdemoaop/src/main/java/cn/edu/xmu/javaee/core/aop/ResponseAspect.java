package cn.edu.xmu.javaee.core.aop;

import cn.edu.xmu.javaee.core.util.ResponseUtil;
import cn.edu.xmu.javaee.core.util.ReturnNo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
public class ResponseAspect {

    private final Logger logger = LoggerFactory.getLogger(ResponseAspect.class);

    @Pointcut("execution(public * cn.edu.xmu.javaee.goodsdemo.controller..*(..))")
    public void wrapResponse(){

    }

    @AfterReturning(pointcut = "wrapResponse()", returning = "retVal")
    public Object doAfter(Object retVal) {
            ReturnNo code = retVal;
            switch (code) {
                case RESOURCE_ID_NOTEXIST:
                    // 404：资源不存在
                    return new ResponseEntity(
                            ResponseUtil.fail(code, e.getMessage()),
                            HttpStatus.NOT_FOUND);

                case AUTH_INVALID_JWT:
                case AUTH_JWT_EXPIRED:
                    // 401
                    return new ResponseEntity(
                            ResponseUtil.fail(code, e.getMessage()),
                            HttpStatus.UNAUTHORIZED);

                case INTERNAL_SERVER_ERR:
                    // 500：数据库或其他严重错误
                    return new ResponseEntity(
                            ResponseUtil.fail(code, e.getMessage()),
                            HttpStatus.INTERNAL_SERVER_ERROR);

                case FIELD_NOTVALID:
                case IMG_FORMAT_ERROR:
                case IMG_SIZE_EXCEED:
                case PARAMETER_MISSED:
                    // 400
                    return new ResponseEntity(
                            ResponseUtil.fail(code, e.getMessage()),
                            HttpStatus.BAD_REQUEST);

                case RESOURCE_ID_OUTSCOPE:
                case FILE_NO_WRITE_PERMISSION:
                    // 403
                    return new ResponseEntity(
                            ResponseUtil.fail(code, e.getMessage()),
                            HttpStatus.FORBIDDEN);
                default:
                    if (null != data) {
                        return ResponseUtil.fail(code, e.getMessage(), data);
                    } else {
                        return ResponseUtil.fail(code, e.getMessage());
                    }

            }
        }
        else{
            // 200: 无错误
            if (data != null) {
                return ResponseUtil.ok(data);
            } else {
                return ResponseUtil.ok();
            }
        }
    }

}
