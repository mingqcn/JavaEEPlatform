package cn.edu.xmu.javaee.restfuldemo.service.bo;

import lombok.Data;

import java.util.List;

/**
 * 商品的可选规格
 * @author Ming Qiu
 **/
@Data
public class Specification {

    private String specName;

    private List<SpecItem> specItemList;
}
