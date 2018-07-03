package com.test.core;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.test.entity.Message;

public class Test1 {

	public static void main(String[] args) {
		ClassPathResource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);		
		Message message = beanFactory.getBean("message", Message.class);    //Message是自己写的测试类
		message.printMessage();
	}
}
