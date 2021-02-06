package com.test.thread.kangshen.future;

public class Demo01 {

    private String name;

    private void test() {
        int i = 5;
        int i1 = i / 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
