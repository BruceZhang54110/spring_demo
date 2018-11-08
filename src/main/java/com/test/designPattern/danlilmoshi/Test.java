package com.test.designPattern.danlilmoshi;

public class Test {

	public static void main(String[] args) {
		
		//饿汉模式
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		if (s1 == s2) {
			System.out.println("s1和s2是同一个实例");
		} else {
			System.out.println("s1和s2不是同一个实例");
		}
		
		// 懒汉模式
		
		Singleton2 sl1 = Singleton2.getInstance();
		Singleton2 sl2 = Singleton2.getInstance();
		
		if (sl1 == sl2) {
			System.out.println("sl1和sl2是同一个实例");
		} else {
			System.out.println("sl1和sl2不是同一个实例");
		}
	}
}
