package cn.edu.xmu.javaee.restfuldemo.controller.dto;




import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wuzhicheng
 * @create 2022-12-13 19:16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SimpleProductDraftDto{
    Long id;
    String name;
    Long originalPrice;
    String originPlace;
}
