package cn.edu.xmu.javaee.core.util;

import lombok.Getter;

/**
 * 返回对象
 * @author Ming Qiu
 **/
@Getter
public class ReturnObject {

    /**
     * 错误号
     */
    ReturnNo errNo = ReturnNo.OK;

    /**
     * 自定义的错误码
     */
    String errmsg = null;

    /**
     * 返回值
     */
    private Object data = null;

    /**
     * 默认构造函数，错误码为OK
     */
    public ReturnObject() {
    }

    /**
     * 带值构造函数
     * @param data 返回值
     */
    public ReturnObject(Object data) {
        this();
        this.data = data;
    }

    /**
     * 有错误码的构造函数
     * @param errNo 错误码
     */
    public ReturnObject(ReturnNo errNo) {
        this.errNo = errNo;
    }

    /**
     * 有错误码和自定义message的构造函数
     * @param errNo 错误码
     * @param errmsg 自定义message
     */
    public ReturnObject(ReturnNo errNo, String errmsg) {
        this(errNo);
        this.errmsg = errmsg;
    }

    /**
     * 有错误码，自定义message和值的构造函数
     * @param errNo 错误码
     * @param data 返回值
     */
    public ReturnObject(ReturnNo errNo, Object data) {
        this(errNo);
        this.data = data;
    }

    /**
     * 有错误码，自定义message和值的构造函数
     * @param errNo 错误码
     * @param errmsg 自定义message
     * @param data 返回值
     */
    public ReturnObject(ReturnNo errNo, String errmsg, Object data) {
        this(errNo, errmsg);
        this.data = data;
    }


    /**
     * 错误信息
     * @return 错误信息
     */
    public String getErrmsg() {
        if (null != this.errmsg) {
            return this.errmsg;
        }else{
            return this.errNo.getMessage();
        }
    }
}
