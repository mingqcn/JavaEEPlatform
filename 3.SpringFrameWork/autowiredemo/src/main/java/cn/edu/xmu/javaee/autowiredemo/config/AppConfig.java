// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo.config;

import cn.edu.xmu.javaee.autowiredemo.autowiredbean.Boss;
import cn.edu.xmu.javaee.autowiredemo.autowiredbean.Car;
import cn.edu.xmu.javaee.autowiredemo.autowiredbean.Haval;
import cn.edu.xmu.javaee.autowiredemo.autowiredbean.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public Car haval(){
        return new Haval();
    }

    @Bean
    @Autowired
    public Boss boss(Car car, Office office){
        return new Boss(car, office);
    }
}
