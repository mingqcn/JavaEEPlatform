//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.oomall.payment.dao.bo;

import cn.edu.xmu.javaee.core.model.OOMallObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *  台账
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Ledge extends OOMallObject {
    /**
     * 内部交易号
     */
    private String outNo;

    /**
     * 渠道交易号
     */
    private String transNo;

    /**
     * 金额
     */
    private Long amount;

    /**
     * 交易时间
     */
    private LocalDateTime successTime;

    /**
     * 调账者id
     */
    private Long adjust_id;

    /**
     * 调账者
     */
    private String adjust_name;

    /**
     * 调账时间
     */
    private LocalDateTime adjustTime;
    /**
     * 对账时间
     */
    private LocalDateTime checkTime;

    /**
     * 台账所属商铺渠道
     */
    private ShopChannel shop;

    /**
     * 关联的交易
     * <p>
     * 渠道多出的账目此属性为null
     */
    private Transaction trans;
}
