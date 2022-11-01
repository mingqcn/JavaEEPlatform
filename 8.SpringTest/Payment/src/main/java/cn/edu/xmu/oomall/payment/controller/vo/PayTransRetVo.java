//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayTransRetVo {

    protected Long id;
    /**
     * 创建者id
     */
    protected Long creatorId;

    /**
     * 创建者
     */
    protected String creatorName;

    /**
     * 修改者id
     */
    protected Long modifierId;

    /**
     * 修改者
     */
    protected String modifierName;

    /**
     * 创建时间
     */
    protected LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    protected LocalDateTime gmtModified;
}
