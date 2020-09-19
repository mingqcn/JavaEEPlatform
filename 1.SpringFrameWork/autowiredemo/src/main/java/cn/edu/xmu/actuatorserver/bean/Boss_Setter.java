package cn.edu.xmu.actuatorserver.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 17:01 2020/7/31
 **/
@Component
public class Boss_Setter {

    private Car car;
    private Office office;

    @Autowired
    public void setCar(Car toyota){
        this.car = toyota;
    }

    @Autowired
    public void setOffice(Office office){
        this.office = office;
    }

}
