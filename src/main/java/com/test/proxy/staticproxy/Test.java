package com.test.proxy.staticproxy;

public class Test {
    public static void main(String[] args) {
        // 被代理的类
        Zhangxueyou zhangxueyou = new Zhangxueyou();
        // 代理类
        StarProxy proxy = new StarProxy(zhangxueyou);
        proxy.sing();
        proxy.dance();
    }
}
