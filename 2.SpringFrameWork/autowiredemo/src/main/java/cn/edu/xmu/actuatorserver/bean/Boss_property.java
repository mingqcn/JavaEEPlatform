package cn.edu.xmu.actuatorserver.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 17:06 2020/7/31
 **/
@Component
public class Boss_property {
    @Autowired
    private Car haval;
    @Autowired
    private Office office;
}
