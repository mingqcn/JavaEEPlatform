package cn.edu.xmu.javaee.restfuldemo.controller.vo;

import cn.edu.xmu.javaee.restfuldemo.service.bo.Product;
import lombok.Data;


/**
 * 商品规格视图对象
 * @author Ming Qiu
 **/
@Data
public class ProductVo {


    private String productSn;

    private String desc;



    private Integer counterPrice;



    private Integer retailPrice;



    private Integer weight;

    /**
     * 由Vo对象创建Product对象
     * @return Product对象
     */
    public Product createProduct(){
        Product product = new Product();
        product.setProductSn(this.productSn);
        product.setCounterPrice(this.counterPrice);
        product.setRetailPrice(this.retailPrice);
        product.setWeight(this.weight);
        return product;
    }
}
