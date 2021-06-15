package com.test.hashmaptest;

public enum EnumTest {
    INSTANCE;

    private Object o;
    EnumTest() {
        System.out.println("hello");
        o = new Object();
    }

    public Object getObj() {
        return o;
    }
}
