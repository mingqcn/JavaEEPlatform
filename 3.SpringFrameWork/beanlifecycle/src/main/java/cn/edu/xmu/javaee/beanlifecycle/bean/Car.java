package cn.edu.xmu.javaee.beanlifecycle.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 17:01 2020/7/31
 **/
@Component
public class Car {

    private Logger log = LoggerFactory.getLogger(Car.class);
    public Car() {
        log.info("Car constructor invoke");
    }
}
