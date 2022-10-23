package cn.edu.xmu.javaee.restfuldemo.controller.vo;

import cn.edu.xmu.javaee.restfuldemo.model.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品规格视图对象
 * @author Ming Qiu
 **/
@Data
public class ProductVo {


    @NotBlank
    @NotNull
    private String productSn;

    @NotBlank
    @NotNull
    private String desc;


    @Min(0)
    private Integer counterPrice;


    @Min(0)
    private Integer retailPrice;


    @Min(0)
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
