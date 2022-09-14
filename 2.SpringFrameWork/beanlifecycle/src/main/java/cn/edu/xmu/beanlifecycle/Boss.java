package cn.edu.xmu.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: Ming Qiu
 * @date: Created in 21:14 2020/7/31s
 **/
public class Boss implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String bossName;

    public Boss() {
        System.out.println("Boss constructor invoke");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Boss.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Boss.setBeanName invoke");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Boss.destory invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Boss.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Boss.setApplicationContext invoke");
    }

    public String getBossName() {
        return bossName;
    }

    @Value("${Boss.bossName}")
    public void setBossName(String bossName) {
        this.bossName = bossName;
        System.out.println("Boss.setBossName invoke: Boss name="+bossName);
    }

    public void myPostConstruct() {
        System.out.println("Boss.myPostConstruct invoke");
    }

    public void myPreDestory() {
        System.out.println("Boss.myPreDestory invoke");
        System.out.println("---------------destroy-----------------");
    }
}

