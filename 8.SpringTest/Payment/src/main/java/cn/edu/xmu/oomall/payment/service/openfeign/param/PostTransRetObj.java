//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service.openfeign.param;

import cn.edu.xmu.oomall.payment.service.openfeign.WePayService;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @see WePayService#postTransaction()
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostTransRetObj {
    /**
     * 预支付交易会话标识
     */
    private String prepayId;
}
