package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Ming Qiu
 * @date: Created in 16:00 2020/10/1
 **/
@Data
@ApiModel(value = "商品的可选规格的项")
public class SpecItem {
    @ApiModelProperty(name = "规格可选项id")
    private String id;

    @ApiModelProperty(name = "规格可选内容")
    private String content;
}
