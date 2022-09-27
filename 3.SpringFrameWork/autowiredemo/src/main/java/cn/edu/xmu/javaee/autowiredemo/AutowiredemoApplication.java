package cn.edu.xmu.javaee.autowiredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.autowiredemo.autowiredbean","cn.edu.xmu.javaee.autowiredemo.cirualbean.constructor"})

@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.autowiredemo.autowiredbean","cn.edu.xmu.javaee.autowiredemo.cirualbean.set"})
public class AutowiredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutowiredemoApplication.class, args);
    }

}
