package com.test.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class FactoryTest implements FactoryBean<String> {

	@Override
	public String getObject() throws Exception {
		// TODO Auto-generated method stub
		return "test12345";
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() {
		return "A FactoryBean";
	}
	
	public static void main(String[] args) {
		
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		// 注册一个FactoryBean类型的BeanDefinition
		factory.registerBeanDefinition("test", new RootBeanDefinition(FactoryTest.class));
		// 获取Bean，也就是调用了FactoryBean的getObject
		System.out.println(factory.getBean("test"));
		// & 获取FactoryBean
		System.out.println(factory.getBean("&test"));
	}

}
