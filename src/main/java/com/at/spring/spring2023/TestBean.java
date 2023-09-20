package com.at.spring.spring2023;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(MainConfig.class);
        //Person person = applicationContext.getBean("person", Person.class);
    }
}
