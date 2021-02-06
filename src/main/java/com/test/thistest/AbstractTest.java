package com.test.thistest;

public abstract class AbstractTest {
    abstract void a();

    public void test() {
        System.out.println("this is AbstractTest test()");
       this.a();
    }
}

