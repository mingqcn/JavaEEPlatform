package cn.edu.xmu.javaee.goodsdemo.dao.bo;

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

    private Logger logger = LoggerFactory.getLogger(Product.class);

    /**
     * 代理对象
     */
    private Long id;

    private List<Product> otherProduct = new ArrayList<>();

    private List<OnSale> onSaleList = new ArrayList<>();

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String imageUrl;

    private String barcode;

    private String unit;

    private String originPlace;

    private User creator;

    private User modifier;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public Product(ProductPo po) {
        assert null!=po;
        id = po.getId();
        skuSn = po.getSkuSn();
        name = po.getName();
        originalPrice = po.getOriginalPrice();
        originPlace = po.getOriginPlace();
        weight = po.getWeight();
        imageUrl = po.getImageUrl();
        barcode = po.getBarcode();
        unit = po.getUnit();
        if (null != po.getCreatorId()) {
            creator = new User(po.getCreatorId(), po.getName());
        }
        if (null != po.getModifierId()) {
            modifier = new User(po.getModifierId(), po.getModifierName());
        }
        gmtCreate = po.getGmtCreate();
        gmtModified = po.getGmtModified();
    }

    public Product(ProductAllPo po){
        assert null!=po;
        id = po.getId();
        skuSn = po.getSkuSn();
        name = po.getName();
        originalPrice = po.getOriginalPrice();
        originPlace = po.getOriginPlace();
        weight = po.getWeight();
        imageUrl = po.getImageUrl();
        barcode = po.getBarcode();
        unit = po.getUnit();
        if (null != po.getCreatorId()) {
            creator = new User(po.getCreatorId(), po.getName());
        }
        if (null != po.getModifierId()) {
            modifier = new User(po.getModifierId(), po.getModifierName());
        }
        gmtCreate = po.getGmtCreate();
        gmtModified = po.getGmtModified();

        this.addOtherProduct(po.getOtherProduct());
        this.addOnSale(po.getOnSaleList());

    }

    public ProductPo createPo(){
        ProductPo productPo = new ProductPo();
        productPo.setId(id);
        productPo.setSkuSn(skuSn);
        productPo.setName(name);
        productPo.setOriginalPrice(originalPrice);
        productPo.setWeight(weight);
        productPo.setImageUrl(imageUrl);
        productPo.setBarcode(barcode);
        productPo.setUnit(unit);
        return productPo;
    }

    public void addOtherProduct(List<ProductPo> poList){
        for (ProductPo productPo : poList){
            if (productPo.getId().equals(this.id)) {
                continue;
            }
            logger.debug("addOtherProduct - id = {},  add other productid = {}", this.id, productPo.getId() );
            this.otherProduct.add(new Product(productPo));
        }
    }

    public void addOnSale(List<OnSalePo> poList){
        for (OnSalePo onSalePo : poList){
            this.onSaleList.add(new OnSale(onSalePo));
        }
    }
}
