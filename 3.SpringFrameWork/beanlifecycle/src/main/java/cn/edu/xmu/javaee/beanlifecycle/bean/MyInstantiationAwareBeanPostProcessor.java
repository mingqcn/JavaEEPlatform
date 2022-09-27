package cn.edu.xmu.javaee.beanlifecycle.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Ming Qiu
 * @date: Created in 21:04 2020/7/31
 **/
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(MyInstantiationAwareBeanPostProcessor.class);

    //在Bean对象实例化前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        log.info("InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation invoke, name = " + beanName);
        return null;
    }

    //在Bean对象实例化后调用（如调用构造器之后调用）
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        log.info("InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation invoke, name = " + beanName);
        return true;
    }

}
