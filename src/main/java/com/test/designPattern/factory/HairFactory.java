package com.test.designPattern.factory;

import java.util.Map;

/**
 * 发型工厂
 * @author Administrator
 *
 */
public class HairFactory {
	
	/**
	 * 根据类型来创建对象
	 * @param key
	 * @return
	 */
	public HairInterface getHair(String key) {
		
		if ("left".equals(key)) {
			return new Lefthair();
		} else if ("right".equals(key)) {
			return new Righthair();
		}
		return null;
		
	}
	
	/**
	 * 根据类的名称来生产对象
	 * @param className
	 * @return
	 */
	public HairInterface getHairByClass(String className) {
		
		try {
			HairInterface hair = (HairInterface) Class.forName(className).newInstance();
			return hair;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 根据类的名称来生产对象
	 * @param className
	 * @return
	 */
	public HairInterface getHairByClassKey(String key) {
		
		try {
			Map<String, String> map = new PropertiesReader().getProperties();
			String className = map.get(key);
			HairInterface hair = (HairInterface) Class.forName(className).newInstance();
			return hair;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
