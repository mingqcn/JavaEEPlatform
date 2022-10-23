package cn.edu.xmu.javaee.goodsdemo.mapper.manual.po;

import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.ProductPo;
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
public class ProductAllPo {

    /**
     * 代理对象
     */
    private Long id;

    private List<ProductPo> otherProduct = new ArrayList<>();

    private List<OnSalePo> onSaleList = new ArrayList<>();

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

}
