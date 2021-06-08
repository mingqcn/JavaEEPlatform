package cn.edu.xmu.restfuldemo.controller.vo;

import cn.edu.xmu.restfuldemo.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品规格视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品规格视图对象")
public class ProductVo {

    @ApiModelProperty(value = "规格序号")
    @NotBlank
    @NotNull
    private String productSn;

    @NotBlank
    @NotNull
    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "促销价")
    @Min(0)
    private Integer counterPrice;

    @ApiModelProperty(value = "零售价")
    @Min(0)
    private Integer retailPrice;

    @ApiModelProperty(value = "重量(克)")
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
