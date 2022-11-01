//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.javaee.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OOMall的通用对象
 */
@Data
@NoArgsConstructor
public class OOMallObject{

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
