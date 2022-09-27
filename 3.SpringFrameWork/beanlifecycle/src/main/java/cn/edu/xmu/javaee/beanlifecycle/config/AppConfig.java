// JavaEEPlatform by School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.beanlifecycle.config;

import cn.edu.xmu.javaee.beanlifecycle.bean.Boss;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "myPostConstruct", destroyMethod = "myPreDestory")
    public Boss boss(){
        return new Boss();
    }
}
