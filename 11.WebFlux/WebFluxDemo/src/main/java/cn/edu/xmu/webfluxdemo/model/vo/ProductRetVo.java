package cn.edu.xmu.webfluxdemo.model.vo;

import lombok.Data;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/22 13:27
 **/
@Data
public class ProductRetVo {
    private Long id;
    private String name;
    private String skuSn;
    private String imageUrl;
    private Integer inventory;
    private Long originalPrice;
    private Long price;
    private Boolean disable;
}
