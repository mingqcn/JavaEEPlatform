//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 通用工具类
 * @author Ming Qiu
 **/
public class Common {


    private static Logger logger = LoggerFactory.getLogger(Common.class);

    /**
     * 生成九位数序号
     * 要保证同一服务的不同实例生成出的序号是不同的
     * @param  platform 机器号 如果一个服务有多个实例，机器号需不同，目前从1至36
     * @return 序号
     */
    public static String genSeqNum(int platform) {
        int maxNum = 36;
        int i;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        LocalDateTime localDateTime = LocalDateTime.now();
        String strDate = localDateTime.format(dtf);
        StringBuffer sb = new StringBuffer(strDate);

        int count = 0;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random r = new Random();
        while (count < 2) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        if (platform > 36){
            platform = 36;
        } else if (platform < 1){
            platform = 1;
        }

        sb.append(str[platform-1]);
        return sb.toString();
    }


    /**
     * 处理BindingResult的错误
     * @param bindingResult
     * @return
     */
    public static Object processFieldErrors(BindingResult bindingResult, HttpServletResponse response) {
        Object retObj = null;
        if (bindingResult.hasErrors()){
            StringBuffer msg = new StringBuffer();
            //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
            for (FieldError error : bindingResult.getFieldErrors()) {
                msg.append(error.getDefaultMessage());
                msg.append(";");
            }
            logger.info("methodArgumentNotValid: msg = "+ msg.toString());
            retObj = ResponseUtil.fail(ReturnNo.FIELD_NOTVALID, msg.toString());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return retObj;
    }

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
                    if (data != null) {
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
