package cn.edu.xmu.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @author: Ming Qiu
 * @date: Created in 21:04 2020/7/31
 **/
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    //在Bean对象实例化前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        System.out.println("InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation invoke, name = " + beanName);
        return null;
    }

    //在Bean对象实例化后调用（如调用构造器之后调用）
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        System.out.println("InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation invoke, name = " + beanName);
        return true;
    }

}
