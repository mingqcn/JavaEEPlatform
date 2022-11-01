//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 分页对象
 * @author Ming Qiu
 **/
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageObj<T> {

    private List<T> list;
    private long total;
    private int page;
    private int pageSize;
    private int pages;
}
