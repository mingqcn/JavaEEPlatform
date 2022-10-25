package cn.edu.xmu.javaee.goodsdemo.dao.bo;

import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.OnSalePoSqlProvider;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.goodsdemo.mapper.manual.po.ProductAllPo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Data
@NoArgsConstructor
public class Product {

    /**
     * 代理对象
     */
    private Long id;

    private List<Product> otherProduct;

    private List<OnSale> onSaleList;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String imageUrl;

    private String barcode;

    private String unit;

    private String originPlace;

    private Long creatorId;
    private String creatorName;


    private Long modifierId;
    private String modifierName;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    public void addOtherProduct(List<Product> otherList){

        if (null == this.otherProduct){
            this.otherProduct = new ArrayList<>(otherList.size());
        }else{
            this.otherProduct.clear();
        }

        for (Product product : otherList){
            if (product.getId().equals(this.id)) {
                continue;
            }
            this.otherProduct.add(product);
        }
    }
}
