//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.controller.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderPayVo {

    @NotNull(message="内部交易号必填")
    private String outNo;

    @NotNull(message="支付渠道必填")
    private Long shopChannelId;

    @NotNull(message="业务Id必填")
    private Long businessId;

    @Min(value = 0, message="付款金额需大于0")
    private Long amount;

    private String spOpenid;

}
