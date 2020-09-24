package cn.edu.xmu.restfuldemo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode()
public class Goods {

    private Integer id;

    private String goodsSn;

    private String name;

    private Integer categoryId;

    private Integer brandId;

    private String[] gallery;

    private String keywords;

    private String brief;

    private Boolean isOnSale;

    private Short sortOrder;

    private String picUrl;

    private String shareUrl;

    private Boolean isNew;

    private Boolean isHot;

    private String unit;

    private BigDecimal counterPrice;

    private BigDecimal retailPrice;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Boolean deleted;

    private String detail;

    private List<GoodsAttribute> goodsAttributeList;

    private List<GoodsSpecification> goodsSpecificationList;
}