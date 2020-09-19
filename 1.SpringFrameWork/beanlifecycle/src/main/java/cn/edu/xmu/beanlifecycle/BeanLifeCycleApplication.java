package cn.edu.xmu.beanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeanLifeCycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanLifeCycleApplication.class, args);
	}

	@Bean(initMethod = "myPostConstruct", destroyMethod = "myPreDestory")
	public Boss boss(){
		return new Boss();
	}

}
