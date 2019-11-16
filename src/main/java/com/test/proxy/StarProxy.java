package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarProxy implements InvocationHandler {
	
	private Object target;
	
	

	public StarProxy(Object target) {
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("收钱");
		Object result = method.invoke(target, args);
		return result;
	}

}
