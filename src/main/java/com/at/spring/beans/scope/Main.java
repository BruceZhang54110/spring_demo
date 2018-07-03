package com.at.spring.beans.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.at.spring.beans.autowire.Car;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
		
		Car car = (Car) ctx.getBean("car");
		
		Car car2 = (Car) ctx.getBean("car");
		System.out.println(car == car2); //返回true ,说明是同一个对象
		
	}
}
