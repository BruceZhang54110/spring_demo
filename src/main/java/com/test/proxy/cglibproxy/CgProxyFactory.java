package com.test.proxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CgProxyFactory implements MethodInterceptor {

    public <T>T getProxy(Class<T> c){
        Enhancer enhancer = new Enhancer();
        // 设置回调
        enhancer.setCallbacks(new Callback[]{this});
        // 设置代理类
        enhancer.setSuperclass(c);
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前-------->");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("执行后-------->");
        return o1;
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:/DEBUG");
        CgProxyFactory factory = new CgProxyFactory();
        GeShou geshou = factory.getProxy(GeShou.class);
        geshou.sing();
    }

}

class GeShou {
    public void sing() {
        System.out.println("我要唱歌");
    }

}