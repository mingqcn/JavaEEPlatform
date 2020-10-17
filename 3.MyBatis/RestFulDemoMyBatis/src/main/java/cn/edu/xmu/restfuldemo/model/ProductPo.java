package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author Ming Qiu
 **/
@Data
@Alias("ProductPo")
public class ProductPo {

    private Integer id;

    private String productSn;

    private String name;

    private Integer goodsId;

    private Integer originalPrice;

    private Integer stock;

    private Integer weight;

    private Integer state;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;
}
