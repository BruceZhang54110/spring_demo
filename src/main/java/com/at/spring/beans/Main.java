package com.at.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*
		 
		// 创建Helloworld 的一个对象
		HelloWorld helloWorld = new HelloWorld();
		// 为name属性赋值
		helloWorld.setName("helloKitty");
		*/
		
		 // 1.创建spring 的IOC容器
		 // ApplicationContext 代表 IOC容器
		// ClassPathXmlApplicationContext 是 ApplicationContext的实现类,该实现类从类路径下加载配置文件
		 // 2.从IOC容器中获取 bean实例
//		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
//		 // 调用方法
//		helloWorld.hello();


		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);

		Person person = (Person) ctx.getBean("person2");
		System.out.println(person);

	}

}
