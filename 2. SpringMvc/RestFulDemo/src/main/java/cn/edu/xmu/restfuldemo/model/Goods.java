package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "商品")
public class Goods {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private String goodsSn;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌id")
    private Integer brandId;

    @ApiModelProperty(value = "商品描述")
     private String brief;

    @ApiModelProperty(value = "商品可选规格")
    private List<Specification> specList;

    @ApiModelProperty(value = "商品规格")
    private List<Product> productList;

    @ApiModelProperty(value = "图片url")
    private String picUrl;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value ="是否上架")
    private Boolean beOnSale;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后一次修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改者")
    private String modiUser;
}