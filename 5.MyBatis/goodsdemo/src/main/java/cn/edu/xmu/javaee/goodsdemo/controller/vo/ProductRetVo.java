//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRetVo {

    private Long id;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String imageUrl;

    private String barcode;

    private String unit;

    private String originPlace;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private List<ProductRetVo> otherProduct;


    /**
     * 构造函数，由Goods对象创建Vo
     * @param product goods对象
     */
    public ProductRetVo(Product product) {
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
        if (product.getOtherProduct().size() > 0) {
            List<ProductRetVo> productList = new ArrayList<>(product.getOtherProduct().size());
            for (Product bo : product.getOtherProduct()) {
                ProductRetVo productVo = new ProductRetVo(bo);
                productList.add(productVo);
            }
            this.otherProduct = productList;
        }
        if (product.getOnSaleList().size()> 0){
            OnSale onSale = product.getOnSaleList().get(0);
            this.price = onSale.getPrice();
            this.quantity = onSale.getQuantity();
        }
    }
}
