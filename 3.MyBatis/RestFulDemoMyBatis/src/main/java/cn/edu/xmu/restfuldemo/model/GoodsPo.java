package cn.edu.xmu.restfuldemo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ming Qiu
 **/
@Data
@Alias("GoodsPo")
public class GoodsPo {

    private Integer id;

    private String goodsSn;

    private String name;

    private Integer categoryId;

    private Integer brandId;

    private String brief;

    private String picUrl;

    private String unit;

    private Integer state;

    private String specList;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Integer modiUser;

    private List<ProductPo> productList;
}
