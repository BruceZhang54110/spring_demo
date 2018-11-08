package com.test.designPattern.danlilmoshi;

/**
 * 懒汉模式
 * 区别：饿汉模式的特点是加载类时比较慢，但是运行时获取对象的速度比较快，线程安全
 *     懒汉模式加载类时比较快，运行时获取对象速度比较慢，第一次获取时，需要创建实例，线程不安全
 *     https://www.cnblogs.com/sunnyDream/p/8011186.html
 * @author Administrator
 *
 */
public class Singleton2 {

	//1.将构造方法私有化
	private Singleton2() {
		
	}
	
	// 2.创建类的唯一实例，使用private static修饰
	private static Singleton2 instance;
	
	// 3.提供获取实例的方法人，使用public static
	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}
