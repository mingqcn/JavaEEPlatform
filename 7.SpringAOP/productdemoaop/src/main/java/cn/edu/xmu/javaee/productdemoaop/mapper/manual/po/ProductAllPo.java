package cn.edu.xmu.javaee.productdemoaop.mapper.manual.po;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAllPo {

    /**
     * 代理对象
     */
    private Long id;

    private List<ProductPo> otherProduct;

    private List<OnSalePo> onSaleList;

    private String skuSn;

    private String name;

    private Long originalPrice;

    private Long weight;

    private String barcode;

    private String unit;

    private String originPlace;

    private Long creatorId;

    private String creatorName;

    private Long modifierId;

    private String modifierName;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer commissionRatio;

    private Long freeThreshold;

    private Byte status;

}
