//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service.channel;

import cn.edu.xmu.oomall.payment.dao.bo.Channel;
import cn.edu.xmu.oomall.payment.dao.bo.PayTrans;
import cn.edu.xmu.oomall.payment.dao.bo.ShopChannel;

/**
 * 支付渠道接口
 */
public interface PayChannel {

    /**
     * 创建支付交易单
     * @author Ming Qiu
     * <p>
     * date: 2022-11-01 19:26
     * @param payTrans 支付交易
     * @return
     */
    public void createPayment(PayTrans payTrans);
}
