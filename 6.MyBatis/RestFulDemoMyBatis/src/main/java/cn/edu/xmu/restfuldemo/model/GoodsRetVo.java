package cn.edu.xmu.rocketmqdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@ApiModel(description = "商品对象")
public class GoodsRetVo {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品序号")
    private String goodsSn;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品描述")
    private String brief;

    @ApiModelProperty(value = "商品图片")
    private String picUrl;

    @ApiModelProperty(value = "商品状态")
    private String state;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "商品类别Id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品品牌Id")
    private Integer brandId;

    @ApiModelProperty(value = "商品可选规格")
    private String specList;

    @ApiModelProperty(value = "商品规格")
    private List<ProductRetVo> productList;

    /**
     * 构造函数，由Goods对象创建Vo
     * @param goods goods对象
     */
    public GoodsRetVo(Goods goods) {
        this.id = goods.getId();
        this.goodsSn = goods.getGoodsSn();
        this.name = goods.getName();
        this.brief = goods.getBrief();
        this.unit = goods.getUnit();
        this.picUrl = goods.getPicUrl();
        this.specList = goods.getSpecList();
        this.brandId = goods.getBrandId();
        this.categoryId = goods.getCategoryId();

        if (null != goods.getState()) {
            this.state = goods.getState().getDescription();
        }
        if (null != goods.getProductList()) {
            List<ProductRetVo> productList = new ArrayList<>(goods.getProductList().size());

            for (Product product : goods.getProductList()) {
                ProductRetVo productVo = new ProductRetVo(product);
                productList.add(productVo);
            }
            this.productList = productList;
        }
    }
}
