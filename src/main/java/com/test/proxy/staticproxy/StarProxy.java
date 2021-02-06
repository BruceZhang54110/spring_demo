package com.test.proxy.staticproxy;

public class StarProxy implements Star {

    private Zhangxueyou zhangxueyou;

    public StarProxy(Zhangxueyou zhangxueyou) {
        this.zhangxueyou = zhangxueyou;
    }

    @Override
    public void sing() {
        System.out.println("收费");
        zhangxueyou.sing();

    }

    @Override
    public void dance() {
        System.out.println("收费");
        zhangxueyou.dance();
    }
}
