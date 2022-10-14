//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.goodsdemo.dao.bo;

import cn.edu.xmu.javaee.goodsdemo.mapper.generator.po.OnSalePo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OnSale {
    private Long id;

    private Long price;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer quantity;

    private User creator;

    private User modifier;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public OnSale(OnSalePo po) {
        assert null!=po;
        id = po.getId();
        price = po.getPrice();
        beginTime = po.getBeginTime();
        endTime = po.getEndTime();
        quantity = po.getQuantity();
        creator = new User(po.getCreatorId(), po.getCreatorName());
        modifier = new User(po.getModifierId(), po.getModifierName());
        gmtCreate = po.getGmtCreate();
        gmtModified = po.getGmtModified();
    }

    public OnSalePo getPo(){
        OnSalePo po = new OnSalePo();
        po.setPrice(price);
        po.setBeginTime(beginTime);
        po.setEndTime(endTime);
        po.setQuantity(quantity);
        return po;
    }

}
