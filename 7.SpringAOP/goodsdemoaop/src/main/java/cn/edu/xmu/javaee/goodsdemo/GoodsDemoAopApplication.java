package cn.edu.xmu.javaee.goodsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.core",
		"cn.edu.xmu.javaee.goodsdemo"})
@MapperScan("cn.edu.xmu.javaee.goodsdemo.mapper")
public class GoodsDemoAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsDemoAopApplication.class, args);
	}

}
