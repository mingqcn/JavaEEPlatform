package cn.edu.xmu.rocketmqdemo.model;

import lombok.Data;

/**
 * 商品规格
 * @author Ming Qiu
 **/
@Data
public class Product {

    private Integer id;

    private String productSn;

    private String desc;

    private Integer goodsId;

    private Integer counterPrice;

    private Integer retailPrice;

    private Integer stock;

    private Integer weight;

    private Boolean beOnSale;

}
