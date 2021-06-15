package com.test.thread.imooc;

public class WaitSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread a waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread a get lock");
                    try {
                        Thread.sleep(20);
                        System.out.println("thread a wait a method");
                        lock.wait(1000);
                        System.out.println("thread a is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread b waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread b get lock");
                    try {
                        System.out.println("thread b is sleep 20");
                        Thread.sleep(10);
                        System.out.println("thread b is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
