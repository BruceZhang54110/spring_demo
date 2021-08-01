package com.at.spring.spring2020;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class XmlBeanFactoryTest {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //System.out.println(applicationContext);
        System.out.println("getBean start");
        System.out.println(bf.getBean("message"));
    }
}
