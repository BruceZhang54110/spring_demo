package com.test.thread.test;

/**
 * 多线程顺序打印ABC
 */
public class ThreadPrint1 {
    private  static int state = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            while (state % 3 == 0) {
                System.out.println("A");
                state++;
            }
        });
        Thread b = new Thread(() -> {
            while (state % 3 == 1) {
                System.out.println("B");
                state++;
            }
        });
        Thread c = new Thread(() -> {
            while (state % 3 == 2) {
                System.out.println("C");
                state++;
            }
        });

        a.start();
        a.join();
        b.start();
        b.join();
        c.start();
    }
}
