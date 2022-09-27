package cn.edu.xmu.javaee.beanlifecycle.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: Ming Qiu
 * @date: Created in 21:14 2020/7/31s
 **/
public class Boss implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private Logger log = LoggerFactory.getLogger(Boss.class);

    private String bossName;

    public Boss() {
        log.info("Boss constructor invoke");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("Boss.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String name) {
        log.info("Boss.setBeanName invoke");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Boss.destory invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Boss.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Boss.setApplicationContext invoke");
    }

    public String getBossName() {
        return bossName;
    }

    @Value("${Boss.bossName}")
    public void setBossName(String bossName) {
        this.bossName = bossName;
        log.info("Boss.setBossName invoke: Boss name="+bossName);
    }

    public void myPostConstruct() {
        log.info("Boss.myPostConstruct invoke");
    }

    public void myPreDestory() {
        log.info("Boss.myPreDestory invoke");
    }
}

