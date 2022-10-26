//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ProductRetVo{

    private Long id;
    private String name;
    private String imageUrl;
    private Long price;
    private Integer quantity;
    private Byte status;
    private String skuSn;

    private Long originalPrice;

    private Long weight;

    private String barcode;

    private String unit;

    private String originPlace;

    private List<SimpleProductRetVo> otherProduct;

    @JsonIgnore
    protected OnSale validOnSale;

    public Long getPrice() {
        if (null != this.validOnSale) {
            return this.validOnSale.getPrice();
        } else {
            return null;
        }
    }

    public Integer getQuantity() {
        if (null != this.validOnSale) {
            return this.validOnSale.getQuantity();
        } else {
            return null;
        }
    }

    /**
     * 获得商品状态
     * @return
     */
    public Byte getStatus() {
        if ((Product.DRAFT == this.status) || (Product.BANNED == this.status)) {
            return status;
        }else{
            if (null == this.getValidOnSale()){
                return Product.OFFSHELF;
            }else{
                return Product.ONSHELF;
            }
        }
    }
}
