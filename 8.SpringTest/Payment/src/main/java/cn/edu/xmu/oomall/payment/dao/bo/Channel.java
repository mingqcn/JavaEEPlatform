//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao.bo;

import cn.edu.xmu.javaee.core.model.OOMallObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商户支付渠道
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Channel extends OOMallObject implements Serializable {

    /**
     * 有效
     */
    public static Byte VALID = 0;
    /**
     * 无效
     */
    public static Byte INVALID = 1;

    /**
     * 平台应用id
     */
    private String appid;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 平台商户号
     */
    private String spMchid;

    /**
     * 开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     *  状态
     */
    private Byte status;

    /**
     * 适配对象名
     */
    private String beanName;

    /**
     * 通知地址
     */
    private String notifyUrl;
}
