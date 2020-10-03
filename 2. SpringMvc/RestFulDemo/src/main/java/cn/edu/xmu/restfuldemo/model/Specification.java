package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;

/**
 * @author: Ming Qiu
 * @date: Created in 15:54 2020/10/1
 **/
@Data
@ApiModel(description = "商品的可选规格")
public class Specification {

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格可选值")
    private List<SpecItem> specItemList;
}
