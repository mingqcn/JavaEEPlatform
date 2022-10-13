package cn.edu.xmu.javaee.goodsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.xmu.javaee.goodsdemo.mapper.generator")
public class GoodsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsDemoApplication.class, args);
	}

}
