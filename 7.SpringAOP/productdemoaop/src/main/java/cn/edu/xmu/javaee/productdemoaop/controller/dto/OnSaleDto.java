package cn.edu.xmu.javaee.productdemoaop.controller.dto;

import cn.edu.xmu.javaee.productdemoaop.dao.bo.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OnSaleDto {
    private Long id;

    private Long price;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer quantity;

    private Integer maxQuantity;

    private User creator;

    private User modifier;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
