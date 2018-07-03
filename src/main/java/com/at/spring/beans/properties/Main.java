package com.at.spring.beans.properties;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.at.spring.beans.autowire.Car;

public class Main {

	public static void main(String[] args) throws SQLException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-properties.xml");
		
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println("aaaaaaaaaa");
		System.out.println(dataSource.getConnection());
		System.out.println("sssssssssssssss");
	}
}
