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

    @ApiModelProperty(value = "规格id")
    private Integer id;

    @ApiModelProperty(value = "规格序号", example = "1-2-3")
    private String productSn;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @ApiModelProperty(value = "促销价")
    private Integer counterPrice;

    @ApiModelProperty(value = "零售价")
    private Integer retailPrice;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "重量(克)")
    private Integer weight;

    @ApiModelProperty(value ="是否上架")
    private Boolean beOnSale;

}
