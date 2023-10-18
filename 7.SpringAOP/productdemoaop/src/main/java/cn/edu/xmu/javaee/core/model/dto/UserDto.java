//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 简单用户西悉尼
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 部门id
     */
    private Long departId;
    /**
     * 用户级别
     */
    private Integer userLevel;

}
