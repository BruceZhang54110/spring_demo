package com.lambda.test;

import java.util.Arrays;
import java.util.List;

public class SimpleTester {

	public static void main(String[] args) {
		String[] data = {"tom", "mike", "Mary", "Jack", "Linda"};
		List<String> names = Arrays.asList(data);
		System.out.println("=====================1.=====================");
		//1、传统方法
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("=====================2.=====================");
		//2、方法2 使用lambda表达式
		names.forEach(name -> System.out.println(name));
		
		System.out.println("====================3.=====================");
		// 3、使用lambda表达式
		names.forEach(System.out::println);
	}

}
