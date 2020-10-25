package cn.edu.xmu.restfuldemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Getter
@Setter
public class Product {

    /**
     * 代理对象
     */
    private ProductPo productPo;
    /**
     * 浮动价格
     */
    private PriceStockPo priceStockPo;

    /**
     * 无参构造函数
     */
    public Product() {
        this.productPo = new ProductPo();
        this.priceStockPo = null;
    }

    /**
     * 构造函数
     * @param productPo 代理对象
     */
    public Product(ProductPo productPo) {
        this.productPo = productPo;
        this.priceStockPo = null;
    }


    public Integer getId() {
        return productPo.getId();
    }

    public Integer getStock() {
        if (null  != this.priceStockPo){
            return this.priceStockPo.getQuantity();
        }else {
            return this.productPo.getStock();
        }
    }

    public String getProductSn() {
        return productPo.getProductSn();
    }

    public String getName() {
        return productPo.getName();
    }

    public Integer getCounterPrice() {
        if (this.priceStockPo != null) {
            return priceStockPo.getPrice();
        }else {
            return productPo.getOriginalPrice();
        }
    }

    public Integer getOriginalPrice() {
        return productPo.getOriginalPrice();
    }

    public Integer getWeight() {
        return productPo.getWeight();
    }

    public void setState(Goods.Status state) {
        productPo.setState(state.getCode());
    }

    public Goods.Status getState() {
        return Goods.Status.getStatusByCode(productPo.getState());
    }

    public void setWeight(Integer weight) {
        productPo.setWeight(weight);
    }

    public LocalDateTime getUpdateTime() {
        return getProductPo().getUpdateTime();
    }

    public LocalDateTime getAddTime() {
        return getProductPo().getAddTime();
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
