package cn.edu.xmu.restfuldemo.model;

import cn.edu.xmu.restfuldemo.model.Goods;
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
    private String productSn;

    @NotBlank
    @ApiModelProperty(value = "描述")
    private String name;

    @ApiModelProperty(value = "零售价")
    @Min(0)
    private Integer originalPrice;

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
        product.setOriginalPrice(this.originalPrice);
        product.setWeight(this.weight);
        product.setName(this.name);
        product.setStock(0);
        product.setState(Goods.Status.UNPUBLISHED);
        return product;
    }
}
