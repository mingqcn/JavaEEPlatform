// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.autowiredbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 17:04 2020/7/31
 **/
@Component
public class Boss_constructor {
    private Car car;
    private Office office;

    @Autowired
    public Boss_constructor(Car toyota, Office office){
        this.car = toyota;
        this.office = office;
    }
}
