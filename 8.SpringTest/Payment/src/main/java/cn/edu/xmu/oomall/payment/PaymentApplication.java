//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.oomall.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Ming Qiu
 */
@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.core",
		"cn.edu.xmu.oomall.payment"})
@MapperScan("cn.edu.xmu.oomall.payment.mapper")
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
