//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnSaleVo {
    private Long id;
    @Min(0)
    private Long price;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    @Min(0)
    private Integer quantity;
}
