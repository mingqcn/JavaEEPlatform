package cn.edu.xmu.webfluxdemo.model.vo;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀视图对象
 * @author Ming Qiu
 **/
@Data
public class FlashSaleItemRetVo {

    private Long id;

    private ProductRetVo goodsSku;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
