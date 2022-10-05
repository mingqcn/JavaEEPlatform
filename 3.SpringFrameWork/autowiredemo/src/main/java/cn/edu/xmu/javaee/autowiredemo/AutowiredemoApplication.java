// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.autowiredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.autowiredemo.config", "cn.edu.xmu.javaee.autowiredemo.autowiredbean", "cn.edu.xmu.javaee.autowiredemo.cirularbean.set"})
public class AutowiredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutowiredemoApplication.class, args);
    }

}
