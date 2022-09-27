package cn.edu.xmu.javaee.beanlifecycle.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 21:07 2020/7/31
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    //实例化完成，setBeanName/setBeanFactory完成之后调用该方法
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        log.info("BeanPostProcessor.postProcessBeforeInitialization invoke,name="+s);
        return o;
    }

    //全部是实例化完成以后调用该方法
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        log.info("BeanPostProcessor.postProcessAfterInitialization invoke,name="+s);
        return o;
    }

}
