package cn.edu.xmu.restfuldemo.controller.vo;

import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.model.Product;
import cn.edu.xmu.restfuldemo.model.Specification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ming Qiu
 * @date: Created in 14:34 2020/10/1
 **/
@Data
@ApiModel(value = "商品")
public class GoodsVo {

    @NotBlank(message="商品名称不能为空")
    @ApiModelProperty(name = "商品名称")
    private String name;

    @ApiModelProperty(name = "商品描述")
    private String brief;

    @ApiModelProperty(name = "商品单位")
    private String unit;

    @ApiModelProperty(name = "商品可选规格")
    private List<Specification> specList;

    @ApiModelProperty(name = "商品规格")
    private List<ProductVo> productList;

    /**
     * 由Vo对象创建Goods对象
     * @return Goods对象
     */
    public Goods createGoods(){
        Goods goods = new Goods();
        goods.setName(this.name);
        goods.setBrief(this.brief);
        goods.setUnit(this.unit);
        goods.setSpecList(this.specList);
        return goods;
    }

}
