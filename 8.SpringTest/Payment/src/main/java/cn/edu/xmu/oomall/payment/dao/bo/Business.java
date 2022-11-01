//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.oomall.payment.dao.bo;

import cn.edu.xmu.javaee.core.model.OOMallObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 业务
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Business extends OOMallObject implements Serializable {
    /**
     * 业务名称
     */
    private String name;
}
