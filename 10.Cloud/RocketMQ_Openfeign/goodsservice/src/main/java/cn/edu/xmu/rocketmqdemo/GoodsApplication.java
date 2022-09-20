package cn.edu.xmu.rocketmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Ming Qiu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GoodsApplication {


	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}

}
