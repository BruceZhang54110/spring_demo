package com.test.proxy;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		// 创建需要被代理的对象
		Liudehua liu = new Liudehua();
		
		//创建委托类
		StarProxy proxy = new StarProxy(liu);
		
		//生成代理类
		Class<?>[] interfaces = Liudehua.class.getInterfaces();
		System.out.println("interfaces:" + interfaces[0].getName());
		Star l = (Star) Proxy.newProxyInstance(Liudehua.class.getClassLoader(),
				interfaces,
				proxy);
		l.dance("a");
		l.sing("l");
	}
}
