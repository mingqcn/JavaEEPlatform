//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.dao.bo;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.OOMallObject;
import cn.edu.xmu.oomall.payment.dao.BusinessDao;
import cn.edu.xmu.oomall.payment.dao.ShopChannelDao;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 交易
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Transaction extends OOMallObject{

    /**
     * 内部交易号
     */
    @Setter
    @Getter
    protected String outNo;

    /**
     * 渠道交易号
     */
    @Setter
    @Getter
    protected String transNo;

    /**
     * 金额
     */
    @Setter
    @Getter
    protected Long amount;

    /**
     * 交易时间
     */
    @Setter
    @Getter
    protected LocalDateTime successTime;

    /**
     * 调账者id
     */
    @Setter
    @Getter
    protected Long adjustId;

    /**
     * 调账者
     */
    @Setter
    @Getter
    protected String adjustName;

    /**
     * 调账时间
     */
    @Setter
    @Getter
    protected LocalDateTime adjustTime;

    @Setter
    @Getter
    protected Long shopChannelId;

    @Setter
    @Getter
    protected Long businessId;
    /**
     * 交易所属的商铺渠道
     */
    protected ShopChannel shop;

    /**
     * 业务
     */
    protected Business business;

    @Setter
    protected ShopChannelDao shopChannelDao;

    @Setter
    protected BusinessDao businessDao;

    public ShopChannel getShop() throws BusinessException {
        if (this.shop != null || null == this.shopChannelDao){
            return this.shop;
        }
        if (null == this.shopChannelId) {
            return null;
        }
        this.shop = this.shopChannelDao.findObjById(shopChannelId);
        return this.shop;
    }

    public Business getBusiness() throws BusinessException{
        if (this.business != null || null == this.businessDao){
            return this.business;
        }
        if (null == this.businessId){
            return null;
        }
        this.business = businessDao.findObjById(this.businessId);
        return this.business;
    }
}
