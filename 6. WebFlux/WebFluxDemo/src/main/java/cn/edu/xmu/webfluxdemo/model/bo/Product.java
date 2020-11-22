package cn.edu.xmu.webfluxdemo.model.bo;

import cn.edu.xmu.webfluxdemo.model.po.GoodsSkuPo;
import cn.edu.xmu.webfluxdemo.model.vo.ProductRetVo;
import cn.edu.xmu.webfluxdemo.model.vo.VoObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/22 13:32
 **/
public class Product implements VoObject, Serializable{
    private Long id;
    private String name;

    private String skuSn;

    private String detail;

    private String imgUrl;

    private Long originalPrice;

    private Integer inventory;

    private Long weight;

    private String configuration;

    private Boolean disabled;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public Product(GoodsSkuPo skuPo) {
        this.id = skuPo.getId();
        this.name = skuPo.getName();
        this.skuSn = skuPo.getSkuSn();
        this.detail = skuPo.getDetail();
        this.imgUrl = skuPo.getImageUrl();
        this.originalPrice = skuPo.getOriginalPrice();
        this.inventory = skuPo.getInventory();
        this.weight = skuPo.getWeight();
        this.configuration = skuPo.getConfiguration();
        this.gmtCreate = skuPo.getGmtCreate();
        this.gmtModified = skuPo.getGmtModified();
        this.disabled = skuPo.getDisabled() == 1;
    }

    @Override
    public Object createVo() {
        ProductRetVo retVo = new ProductRetVo();
        retVo.setId(this.id);
        retVo.setDisable(this.disabled);
        retVo.setImageUrl(this.imgUrl);
        retVo.setInventory(this.inventory);
        retVo.setName(this.name);
        retVo.setOriginalPrice(this.originalPrice);
        retVo.setSkuSn(this.skuSn);
        return retVo;
    }

    public Product() {
        super();
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
