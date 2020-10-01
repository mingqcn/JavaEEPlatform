package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author: Ming Qiu
 * @date: Created in 15:36 2020/10/1
 **/
@Data
@ApiModel(value = "商品规格")
public class Product {

    @ApiModelProperty(name = "规格id")
    private Integer id;

    @ApiModelProperty(name = "规格序号")
    private String productSn;

    @ApiModelProperty(name = "描述")
    private String desc;

    @ApiModelProperty(name = "商品id")
    private Integer goodsId;

    @ApiModelProperty(name = "促销价")
    private Integer counterPrice;

    @ApiModelProperty(name = "零售价")
    private Integer retailPrice;

    @ApiModelProperty(name = "库存量")
    private Integer stock;

    @ApiModelProperty(name = "重量(克)")
    private Integer weight;

    @ApiModelProperty(name ="是否上架")
    private Boolean beOnSale;

}
