//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.oomall.payment.dao.bo;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.OOMallObject;
import cn.edu.xmu.oomall.payment.dao.ChannelDao;
import lombok.*;

import java.io.Serializable;

/**
 * 商铺支付渠道
 */
@ToString(callSuper = true)
@NoArgsConstructor
public class ShopChannel extends OOMallObject implements Serializable {

    /**
     * 有效
     */
    public static Byte VALID = 0;
    /**
     * 无效
     */
    public static Byte INVALID = 1;

    /**
     * 商铺id
     */
    @Getter
    @Setter
    private Long shopId;

    /**
     * 子商户号
     */
    @Getter
    @Setter
    private String subMchid;

    /**
     * 状态
     */
    @Getter
    @Setter
    private Byte status;

    /**
     * 支付渠道
     */
    private Channel channel;

    @Setter
    @Getter
    private Long channelId;

    @Setter
    private ChannelDao channelDao;

    public Channel getChannel() throws BusinessException{
        if (null == this.channelId){
            return null;
        }

        if (null == this.channel && null != channelDao){
            this.channel = this.channelDao.findObjById(this.channelId);
        }
        return this.channel;
    }
}
