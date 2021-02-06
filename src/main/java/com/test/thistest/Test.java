package com.test.thistest;

public class Test extends AbstractTest {
    public static void main(String[] args) {

        Test t = new Test();

    }

    public Test() {
        this.test();
    }

    @Override
    void a() {
        System.out.println("Test ");
    }
}
