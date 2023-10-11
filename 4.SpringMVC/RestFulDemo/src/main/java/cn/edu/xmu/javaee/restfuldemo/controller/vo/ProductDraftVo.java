package cn.edu.xmu.javaee.restfuldemo.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品草稿
 *
 */
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDraftVo {
    @NotNull(message = "名称不能为空")
    private String name;

    @Min(value = 0, message = "原价不能小于0")
    private Long originalPrice;

    private Long categoryId;

    private String unit;

    private String originPlace;
}
