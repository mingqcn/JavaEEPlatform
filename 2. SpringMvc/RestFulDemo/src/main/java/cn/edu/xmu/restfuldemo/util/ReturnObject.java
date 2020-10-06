package cn.edu.xmu.restfuldemo.util;

import lombok.Data;

/**
 * 返回对象
 * @author Ming Qiu
 **/
@Data
public class ReturnObject<T> {

    /**
     * 错误号
     */
    Integer errNo = 0;

    /**
     * 错误消息
     */
    String errMsg = "";

    /**
     * 返回值
     */
    private T data = null;

}
