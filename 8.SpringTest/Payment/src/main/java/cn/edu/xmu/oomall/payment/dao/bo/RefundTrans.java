//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.oomall.payment.dao.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 退款交易
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RefundTrans extends Transaction{

    /**
     * 待退款
     */
    public static Byte NOTREFUND = 0;
    /**
     * 已退款
     */
    public static Byte REFUNDED = 1;
    /**
     * 已对账
     */
    public static Byte CHECKED = 2;
    /**
     * 错账
     */
    public static Byte WRONG = 3;
    /**
     * 退款失败
     */
    public static Byte REFOUDFAIL = 4;
    /**
     * 退款取消
     */
    public static Byte CANCEL = 5;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 用户退回账号
     */
    private String userReceivedAccount;

    /**
     * 关联的支付交易
     */
    private PayTrans payTrans;

}
