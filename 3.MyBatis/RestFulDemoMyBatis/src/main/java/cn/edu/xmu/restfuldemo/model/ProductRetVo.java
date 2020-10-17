package cn.edu.xmu.restfuldemo.model;

import cn.edu.xmu.restfuldemo.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 商品规格视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品规格视图对象")
public class ProductRetVo {

    @ApiModelProperty(value = "规格id")
    private Integer id;

    @ApiModelProperty(value = "规格序号")
    private String productSn;

    @ApiModelProperty(value = "描述")
    private String name;

    @ApiModelProperty(value = "零售价")
    private Integer originalPrice;

    @ApiModelProperty(value = "促销价")
    private Integer counterPrice;

    @ApiModelProperty(value = "重量(克)")
    private Integer weight;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "规格状态")
    private Integer state;

    /**
     * 由Vo对象创建Product对象
     * @return Product对象
     */
    public ProductRetVo(Product product){
        this.id = product.getId();
        this.productSn = product.getProductSn();
        this.originalPrice = product.getOriginalPrice();
        this.counterPrice = product.getCounterPrice();
        this.weight = product.getWeight();
        this.name = product.getName();
        this.stock = product.getStock();
        this.state = product.getState().getCode();
    }
}
