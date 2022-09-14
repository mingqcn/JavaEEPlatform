package cn.edu.xmu.rocketmqdemo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = "cn.edu.xmu.rocketmqdemo.microservice")
public class ConsumerApplication{

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
