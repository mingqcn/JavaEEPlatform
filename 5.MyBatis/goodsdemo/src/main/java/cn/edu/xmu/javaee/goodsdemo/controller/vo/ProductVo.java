//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVo {

    private static Logger logger = LoggerFactory.getLogger(ProductVo.class);

    private Long id;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String imageUrl;

    private String barcode;

    private String unit;

    private String originPlace;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private User creator;

    private User modifier;


    /**
     * 由Vo对象创建Goods对象
     * @return Goods对象
     */
    public Product createBo(){
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

    public ProductVo(Product product) {
        this.id = product.getId();
        this.skuSn = product.getSkuSn();
        this.name = product.getName();
        this.unit = product.getUnit();
        this.originalPrice = product.getOriginalPrice();
        this.barcode = product.getBarcode();
        this.imageUrl = product.getImageUrl();
        this.weight = product.getWeight();
        this.originPlace = product.getOriginPlace();
        this.gmtCreate = product.getGmtCreate();
        this.gmtModified = product.getGmtModified();
        this.creator = product.getCreator();
        this.modifier = product.getModifier();
    }

}
