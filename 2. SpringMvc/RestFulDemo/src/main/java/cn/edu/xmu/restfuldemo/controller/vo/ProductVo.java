package cn.edu.xmu.restfuldemo.controller.vo;

import cn.edu.xmu.restfuldemo.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Ming Qiu
 * @date: Created in 15:45 2020/10/1
 **/
@Data
@ApiModel(value = "商品规格")
public class ProductVo {

    @ApiModelProperty(name = "规格序号")
    @NotBlank
    @NotNull
    private String productSn;

    @NotBlank
    @NotNull
    @ApiModelProperty(name = "描述")
    private String desc;

    @ApiModelProperty(name = "促销价")
    @Min(0)
    private Integer counterPrice;

    @ApiModelProperty(name = "零售价")
    @Min(0)
    private Integer retailPrice;

    @ApiModelProperty(name = "重量(克)")
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
