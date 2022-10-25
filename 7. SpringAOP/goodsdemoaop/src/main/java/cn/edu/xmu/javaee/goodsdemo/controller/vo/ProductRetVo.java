//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import cn.edu.xmu.javaee.goodsdemo.dao.bo.OnSale;
import cn.edu.xmu.javaee.goodsdemo.dao.bo.Product;
import com.alibaba.druid.filter.AutoLoad;
import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品视图对象
 * @author Ming Qiu
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
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

    @JsonIgnore
    private List<OnSale> onSaleList;

    public Long getPrice() {
        OnSale valid = this.findValidOnSale();
        if (null != valid) {
            return valid.getPrice();
        } else {
            return null;
        }
    }

    public Integer getQuantity() {
        OnSale valid = this.findValidOnSale();
        if (null != valid) {
            return valid.getQuantity();
        } else {
            return null;
        }
    }

    /**
     * 获得当前有效的销售对象
     * @return
     */
    private OnSale findValidOnSale(){
        if ((null != this.onSaleList) && (onSaleList.size()) > 0) {
            return this.onSaleList.get(0);
        } else {
            return null;
        }
    }
}
