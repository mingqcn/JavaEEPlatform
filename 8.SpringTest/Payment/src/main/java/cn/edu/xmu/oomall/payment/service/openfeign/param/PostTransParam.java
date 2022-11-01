//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service.openfeign.param;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostTransParam {
    /**
     * 服务商户号
     */
    private String sp_mchid;
    /**
     * 子商户号
     */
    private String sub_mchid;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 服务商应用ID
     */
    private String sp_appid;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 通知地址
     */
    private String notify_url;

    /**
     *  订单金额
     */
    private Amount amount;

    /**
     * 支付者
     */
    private Payer payer;

    @Data
    @NoArgsConstructor
    public class Amount {
        /**
         * 总金额
         */
        private Long total;
        /**
         *  货币类型
         */
        private String currency;

    }

    @Data
    @NoArgsConstructor
    public class Payer {
        /**
         *  用户服务标识
         */
         private String sp_openid;
    }


}
