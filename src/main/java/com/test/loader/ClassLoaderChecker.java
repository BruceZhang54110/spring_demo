package com.test.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderChecker {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MyClassLoader classLaoder = new MyClassLoader("C:\\testJava\\", "myClassLoader");
		Class c = classLaoder.loadClass("Hello");
		System.out.println(c.getClassLoader());
		Method[] declaredMethods = c.getDeclaredMethods();
		declaredMethods[0].invoke(null, new Object[] { new String[] {} });
	}
}
