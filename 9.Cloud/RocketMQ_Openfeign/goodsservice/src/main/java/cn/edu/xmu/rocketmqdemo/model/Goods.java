package cn.edu.xmu.rocketmqdemo.model;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Goods {

    private Integer id;

    private String goodsSn;

    private String name;

    private Integer categoryId;

    private Integer brandId;

     private String brief;

    private List<Specification> specList;

    private List<Product> productList;

    private String picUrl;

    private String unit;

    private Boolean beOnSale;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private String modiUser;
}