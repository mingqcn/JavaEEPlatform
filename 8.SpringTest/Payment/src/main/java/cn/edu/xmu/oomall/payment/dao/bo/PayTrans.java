//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao.bo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付交易
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PayTrans extends Transaction{

    /**
     * 未支付
     */
    public static Byte NOTPAID = 0;
    /**
     * 已支付
     */
    public static Byte PAID = 1;
    /**
     * 已对账
     */
    public static Byte CHECKED = 2;
    /**
     * 错账
     */
    public static Byte WRONG = 3;
    /**
     * 支付失败
     */
    public static Byte PAYFAIL = 4;
    /**
     * 取消
     */
    public static Byte CANCEL = 5;
    /**
     * 退款
     */
    public static Byte REFOUND = 6;

    /**
     * 支付用户id
     */
    @Setter
    @Getter
    private String spOpenid;

    /**
     * 交易结束时间
     */
    @Setter
    @Getter
    private LocalDateTime timeExpire;

    /**
     * 交易开始时间
     */
    @Setter
    @Getter
    private LocalDateTime timeBegin;

    /**
     * 预支付id
     */
    @Setter
    @Getter
    private String prepayId;

    /**
     * 状态
     */
    @Setter
    @Getter
    private Byte status;

    /**
     * 关联的退款交易
     */
    private List<RefundTrans> refundTransList;
}
