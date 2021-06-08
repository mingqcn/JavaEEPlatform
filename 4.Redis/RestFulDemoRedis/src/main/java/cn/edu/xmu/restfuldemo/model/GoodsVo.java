package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品视图对象")
public class GoodsVo {

    private static Logger logger = LoggerFactory.getLogger(GoodsVo.class);

    @NotBlank(message="商品名称不能为空")
    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品描述")
    private String brief;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "商品类别Id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品品牌Id")
    private Integer brandId;

    @ApiModelProperty(value = "商品可选规格")
    private String specList;

    @ApiModelProperty(value = "商品规格")
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
        goods.setBrandId(this.brandId);
        goods.setCategoryId(this.categoryId);
        goods.setState(Goods.Status.UNPUBLISHED);

        if (null != this.productList) {
            logger.info("createGoods: this.productList = "+this.productList);
            List<Product> newProductList = new ArrayList<>(this.productList.size());

            for (ProductVo productVo : this.productList) {
                Product product = productVo.createProduct();
                newProductList.add(product);
            }
            goods.setProductList(newProductList);
        }

        return goods;
    }

}
