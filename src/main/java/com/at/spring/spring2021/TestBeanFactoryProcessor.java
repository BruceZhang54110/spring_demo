package com.at.spring.spring2021;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TestBeanFactoryProcessor {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans-beanFactoryPostProcessor.xml");
        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) classPathXmlApplicationContext.getBean("bfpp");
        //bfpp.postProcessBeanFactory(classPathXmlApplicationContext.getBeanFactory());
        System.out.println(classPathXmlApplicationContext.getBean("car"));

    }

    public static void setLogConfig() throws IOException {
        final Properties properties = new Properties();
        properties.load(TestBeanFactoryProcessor.class.getClassLoader().getResourceAsStream("log4j.properties"));
        PropertyConfigurator.configure(properties);
        // 参数存储
    }

}
