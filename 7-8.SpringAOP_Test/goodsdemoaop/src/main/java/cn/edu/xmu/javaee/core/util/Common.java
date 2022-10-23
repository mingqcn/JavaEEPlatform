//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

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

    }
}
