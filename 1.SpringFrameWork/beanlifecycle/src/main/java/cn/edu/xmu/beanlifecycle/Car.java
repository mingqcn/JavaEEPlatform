package cn.edu.xmu.beanlifecycle;

import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 17:01 2020/7/31
 **/
@Component
public class Car {
    public Car() {
        System.out.println("Car constructor invoke");
    }
}
