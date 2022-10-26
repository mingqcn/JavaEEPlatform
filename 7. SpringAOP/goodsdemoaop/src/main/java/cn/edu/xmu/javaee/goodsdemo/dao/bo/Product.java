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
     * 共四种状态
     */
    //草稿
    public static final  Byte DRAFT = 0;
    //下架
    public static final  Byte OFFSHELF  = 1;
    //上架
    public static final  Byte ONSHELF  = 2;
    //禁售中
    public static final  Byte BANNED = 3;

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

    private Byte status = DRAFT;

    private Long creatorId;
    private String creatorName;

    private Long modifierId;

    private String modifierName;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    /**
     * 增加其他商品
     * @param otherList
     */
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

    /**
     * 获得有效的销售
     * @return
     */
    public OnSale getValidOnSale(){
        OnSale validOnSale = null;
        LocalDateTime now = LocalDateTime.now();
        if (null != this.otherProduct) {
            for (OnSale sale : this.onSaleList) {
                if (now.isAfter(sale.getBeginTime()) && now.isBefore(sale.getEndTime())) {
                    validOnSale = sale;
                    break;
                }
            }
        }
        return validOnSale;
    }
}
