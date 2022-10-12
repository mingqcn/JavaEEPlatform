//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
public class ProductVo {

    private static Logger logger = LoggerFactory.getLogger(ProductVo.class);

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String imageUrl;

    private String barcode;

    private String unit;

    private String originPlace;


    /**
     * 由Vo对象创建Goods对象
     * @return Goods对象
     */
    public Product getBo(){
        Product product = new Product();
        product.setName(this.name);
        product.setOriginalPrice(this.originalPrice);
        product.setWeight(this.weight);
        product.setImageUrl(this.imageUrl);
        product.setBarcode(this.barcode);
        product.setUnit(this.unit);
        product.setOriginPlace(this.originPlace);
        return product;
    }

}
