package cn.edu.xmu.rocketmqdemo;

import cn.edu.xmu.rocketmqdemo.service.RocketMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

	@Autowired
	private RocketMQService rocketMQService;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Long orderId = Long.valueOf(3);

		rocketMQService.sendOrderPayMessage(orderId);
	}
}
