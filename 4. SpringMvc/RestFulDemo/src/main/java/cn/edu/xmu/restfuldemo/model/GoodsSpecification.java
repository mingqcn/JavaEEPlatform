package cn.edu.xmu.restfuldemo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@ToString
@EqualsAndHashCode()
public class GoodsSpecification {

    private Integer id;

    private Integer goodsId;

    private String specification;

    private String value;

    private String picUrl;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Boolean deleted;

}