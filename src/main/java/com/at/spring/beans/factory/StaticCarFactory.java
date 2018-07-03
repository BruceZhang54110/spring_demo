package com.at.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法 : 直接调用某个类的静态方法可以返回bean的实例
 * @author Administrator
 *
 */
public class StaticCarFactory {
	
	private static Map<String, Car> cars = new HashMap<>();
	
	static {
		cars.put("audi", new Car("audi", 300000));
		cars.put("ford", new Car("audi", 700000));
	}

	/**
	 * 静态工厂方法， 
	 * @param name
	 * @return
	 */
	public static Car getCar(String name) {
		return cars.get(name);
	}
}
