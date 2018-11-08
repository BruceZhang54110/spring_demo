package com.test.designPattern.danlilmoshi;

/**
 * 单例模式 Singleton
 * 应用场合：有些对象只需要有一个就够了，如古代的皇帝
 * 作用： 保证整个应用程序中某个实例有且只有一个
 * 类型：饿汉模式，懒汉模式
 * 本代码是饿汉模式
 * @author Administrator
 *
 */
public class Singleton {
	// 1.将构造方法私有化，不允许外部直接创建对象
	private Singleton() {
		
	}
	
	// 2.创建类的唯一实例，使用private static
	private static Singleton instance = new Singleton();
	
	// 3.提供用于获取实例的方法,使用 public static
	public static Singleton getInstance() {
		return instance;
	}
	
	
}
