//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 简单用户西悉尼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleUser {
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
