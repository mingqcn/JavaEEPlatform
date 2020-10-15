package cn.edu.xmu.restfuldemo.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象
 * @author Ming Qiu
 **/
@Data
public class ReturnObject<T> {

    /**
     * 错误号
     */
    Integer errno = 0;

    /**
     * 错误消息
     */
    String errmsg = "";

    /**
     * 返回值
     */
    private T data = null;

    public ReturnObject() {
        this.errno = ResponseCode.OK;
        this.errmsg = ResponseCode.OK_MSG;
    }

    public ReturnObject(T data) {
        this.errno = ResponseCode.OK;
        this.errmsg = ResponseCode.OK_MSG;
        this.data = data;
    }

    public ReturnObject(Integer errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }

}
