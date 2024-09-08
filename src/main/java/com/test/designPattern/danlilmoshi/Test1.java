package com.test.designPattern.danlilmoshi;

public class Test1 {

    private volatile static Test1 instance;

    private Test1() {

    }

    public static Test1 getInstance() {

        if (instance == null) {
            synchronized (Test1.class) {
                if (instance == null) {
                    instance = new Test1();
                }
                return instance;
            }
        } else {
            return instance;
        }
    }
}
