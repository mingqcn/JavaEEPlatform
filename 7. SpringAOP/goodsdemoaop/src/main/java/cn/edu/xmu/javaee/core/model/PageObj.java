//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 * @author Ming Qiu
 **/

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageObj<T> {

    private List<T> list;
    private long total;
    private int page;
    private int pageSize;
    private int pages;

    public PageObj(List<T> list, long total, int page, int pageSize, int pages) {
        this.list = list;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.pages = pages;
    }
}
