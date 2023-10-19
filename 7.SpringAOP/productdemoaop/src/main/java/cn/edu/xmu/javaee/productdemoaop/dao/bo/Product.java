package cn.edu.xmu.javaee.productdemoaop.dao.bo;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Data
@NoArgsConstructor
public class Product {

    private static final Logger logger = LoggerFactory.getLogger(Product.class);

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

    private String barcode;

    private String unit;

    private String originPlace;

    private Integer commissionRatio;

    private Long freeThreshold;

    private byte status;

    private User creator;

    private User modifier;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    @Builder

    public Product(Long id, String skuSn, String name, Long originalPrice, Long weight, String barcode, String unit, String originPlace, Integer commissionRatio, Long freeThreshold, byte status, User creator, User modifier, LocalDateTime gmtCreate, LocalDateTime gmtModified) {
        this.id = id;
        this.skuSn = skuSn;
        this.name = name;
        this.originalPrice = originalPrice;
        this.weight = weight;
        this.barcode = barcode;
        this.unit = unit;
        this.originPlace = originPlace;
        this.commissionRatio = commissionRatio;
        this.freeThreshold = freeThreshold;
        this.status = status;
        this.creator = creator;
        this.modifier = modifier;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
