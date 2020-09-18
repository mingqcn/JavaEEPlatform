package cn.edu.xmu.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: Ming Qiu
 * @date: Created in 21:14 2020/7/31
 **/
public class Book implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String bookName;

    public Book() {
        System.out.println("Book constructor invoke");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Book.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Book.setBeanName invoke");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Book.destory invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Book.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Book.setApplicationContext invoke");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("Book.setBookName invoke: Book name="+bookName);
    }

    public void myPostConstruct() {
        System.out.println("Book.myPostConstruct invoke");
    }

    public void myPreDestory() {
        System.out.println("Book.myPreDestory invoke");
        System.out.println("---------------destroy-----------------");
    }
}

