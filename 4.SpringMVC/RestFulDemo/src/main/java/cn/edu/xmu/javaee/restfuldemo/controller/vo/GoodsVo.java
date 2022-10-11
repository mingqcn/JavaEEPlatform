package cn.edu.xmu.javaee.restfuldemo.controller.vo;

import cn.edu.xmu.javaee.restfuldemo.service.bo.Goods;
import cn.edu.xmu.javaee.restfuldemo.service.bo.Specification;
import lombok.Data;

import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
public class GoodsVo {

    private String name;

    private String brief;


    private String unit;


    private Integer categoryId;


    private Integer brandId;


    private List<Specification> specList;


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
        return goods;
    }

}
