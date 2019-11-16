package com.test.proxy;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		// 创建需要被代理的对象
		Liudehua liu = new Liudehua();
		
		//创建委托类
		StarProxy proxy = new StarProxy(liu);
		
		//生成代理类
		Star l = (Star) Proxy.newProxyInstance(Liudehua.class.getClassLoader(), 
				Liudehua.class.getInterfaces(),
				proxy);
		l.dance("a");
		l.sing("l");
	}
}
