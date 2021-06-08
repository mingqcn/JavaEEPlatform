package cn.edu.xmu.restfuldemo.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author Ming Qiu
 **/
@Data
@Alias("PriceStockPo")
public class PriceStockPo {

    private Integer id;

    private Integer productId;

    private Integer price;

    private Integer quantity;

    private LocalDateTime begintime;

    private LocalDateTime endTime;
}
