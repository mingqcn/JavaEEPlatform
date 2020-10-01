package cn.edu.xmu.restfuldemo.model;

import cn.edu.xmu.restfuldemo.util.ReturnObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "商品")
public class Goods {

    @ApiModelProperty(name = "商品id")
    private Integer id;

    @ApiModelProperty(name = "商品编号")
    private String goodsSn;

    @ApiModelProperty(name = "商品名称")
    private String name;

    @ApiModelProperty(name = "类别id")
    private Integer categoryId;

    @ApiModelProperty(name = "品牌id")
    private Integer brandId;

    @ApiModelProperty(name = "商品描述")
     private String brief;

    @ApiModelProperty(name = "商品可选规格")
    private List<Specification> specList;

    @ApiModelProperty(name = "商品规格")
    private List<Product> productList;

    @ApiModelProperty(name = "图片url")
    private String picUrl;

    @ApiModelProperty(name = "单位")
    private String unit;

    @ApiModelProperty(name ="是否上架")
    private Boolean beOnSale;

    @ApiModelProperty(name = "创建时间")
    private LocalDateTime addTime;

    @ApiModelProperty(name = "最后一次修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "修改者")
    private String modiUser;
}