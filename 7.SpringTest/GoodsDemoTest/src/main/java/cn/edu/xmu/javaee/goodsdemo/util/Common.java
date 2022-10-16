//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 通用工具类
 * @author Ming Qiu
 **/
public class Common {

    private static Logger logger = LoggerFactory.getLogger(Common.class);

    /**
     * 根据 errCode 修饰 API 返回对象的 HTTP Status
     *
     * @param data 原返回 Object
     * @param e BusinessException
     * @return 修饰后的返回 Object
     */
    public static Object returnWithStatus(Object data, BusinessException e) {
        if (null != e){
            ReturnNo code = e.getErrno();
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
