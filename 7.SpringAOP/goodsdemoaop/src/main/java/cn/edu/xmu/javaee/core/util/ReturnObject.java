//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 返回对象
 * @author Ming Qiu
 **/
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnObject {

    /**
     * 错误号
     */
    @JsonIgnore
    ReturnNo code;

    /**
     * 自定义的错误码
     */
    @JsonProperty(value = "errmsg")
    String errMsg;

    /**
     * 返回值
     */
    @JsonProperty(value = "data")
    private Object data = null;

    /**
     * 默认构造函数，错误码为OK
     */
    public ReturnObject() {
        this.code = ReturnNo.OK;
        this.errMsg = ReturnNo.OK.getMessage();
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
     * @param code 错误码
     */
    public ReturnObject(ReturnNo code) {
        this.code = code;
        this.errMsg = code.getMessage();
    }

    /**
     * 有错误码和自定义message的构造函数
     * @param code 错误码
     * @param errMsg 自定义message
     */
    public ReturnObject(ReturnNo code, String errMsg) {
        this(code);
        this.errMsg = errMsg;
    }

    /**
     * 有错误码，自定义message和值的构造函数
     * @param code 错误码
     * @param data 返回值
     */
    public ReturnObject(ReturnNo code, Object data) {
        this(code);
        this.data = data;
    }

    /**
     * 有错误码，自定义message和值的构造函数
     * @param code 错误码
     * @param errMsg 自定义message
     * @param data 返回值
     */
    public ReturnObject(ReturnNo code, String errMsg, Object data) {
        this(code, errMsg);
        this.data = data;
    }

    /**
     * 错误码
     * @return 错误码
     */
    @JsonProperty(value = "errno")
    public int getErrno(){
        return this.code.getErrNo();
    }

}
