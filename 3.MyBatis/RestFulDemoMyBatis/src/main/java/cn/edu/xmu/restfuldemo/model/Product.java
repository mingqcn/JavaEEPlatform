package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品规格")
public class Product {

    private ProductPo productPo;
    private PriceStockPo priceStockPo;

    /**
     * 无参构造函数
     */
    public Product() {
        this.productPo = new ProductPo();
    }

    /**
     * 构造函数
     * @param productPo 代理对象
     */
    public Product(ProductPo productPo) {
        this.productPo = productPo;
    }

    @ApiModelProperty(value = "规格id")
    public Integer getId() {
        return productPo.getId();
    }

    @ApiModelProperty(value = "库存量")
    public Integer getStock() {
        if (priceStockPo != null){
            return priceStockPo.getQuantity();
        }else {
            return productPo.getStock();
        }
    }

    @ApiModelProperty(value = "规格序号", example = "1-2-3")
    public String getProductSn() {
        return productPo.getProductSn();
    }

    @ApiModelProperty(value = "描述")
    public String getName() {
        return productPo.getName();
    }

    @ApiModelProperty(value = "促销价")
    public Integer getCounterPrice() {
        if (this.priceStockPo != null) {
            return priceStockPo.getPrice();
        }else {
            return productPo.getOriginalPrice();
        }
    }

    @ApiModelProperty(value = "零售价")
    public Integer getOriginalPrice() {
        return productPo.getOriginalPrice();
    }

    @ApiModelProperty(value = "重量(克)")
    public Integer getWeight() {
        return productPo.getWeight();
    }

    public void setStatus(Goods.Status state) {
        productPo.setState(state.getCode());
    }

    @ApiModelProperty(value ="规格状态")
    public Goods.Status getState() {
        return Goods.Status.getStatusByCode(productPo.getState());
    }

    public void setWeight(Integer weight) {
        productPo.setWeight(weight);
    }

    public void setId(Integer id) {
        productPo.setId(id);
    }

    public void setStock(Integer stock) {
        productPo.setStock(stock);
    }

    public void setProductSn(String productSn) {
        productPo.setProductSn(productSn);
    }

    public void setOriginalPrice(Integer counterPrice) {
        productPo.setOriginalPrice(counterPrice);
    }


    public void setName(String desc) {
        productPo.setName(desc);
    }

    @Override
    public boolean equals(Object o) {
        return productPo.equals(o);
    }

    @Override
    public int hashCode() {
        return productPo.hashCode();
    }

    @Override
    public String toString() {
        return productPo.toString();
    }
}
