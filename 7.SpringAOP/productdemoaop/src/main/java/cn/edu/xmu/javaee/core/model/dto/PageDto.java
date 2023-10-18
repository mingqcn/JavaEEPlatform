//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 分页对象
 * @author Ming Qiu
 **/
@Getter
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto<T> {
    /**
     * 对象列表
     */
    private List<T> list;
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页数目
     */
    private int pageSize;
}
