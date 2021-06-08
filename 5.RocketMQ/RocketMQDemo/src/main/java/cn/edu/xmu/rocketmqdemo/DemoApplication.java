package cn.edu.xmu.rocketmqdemo;

import cn.edu.xmu.rocketmqdemo.model.Log;
import cn.edu.xmu.rocketmqdemo.service.RocketMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private RocketMQService rocketMQService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Log log = new Log();
		log.setUserId(Long.valueOf(1));
		log.setPrivId(Long.valueOf(2));
		log.setGmtCreate(LocalDateTime.now());
		rocketMQService.sendLogMessage(log);

		Long orderId = Long.valueOf(3);
		rocketMQService.sendOrderPayMessage(orderId);
	}
}
