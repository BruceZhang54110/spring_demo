package com.test.thread.mianshi;

import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;

public class PrintABC123 {
    private static int count = 26;
    private static int start = 0;
    private static char[] c = new char[]{'A','B'};
    private static Object monitor = new Object();

    private static void print() {
        while(start <= count) {
            synchronized(monitor) {
                if (Thread.currentThread().getName().equals("number")) {
                    System.out.println(start++);
                    monitor.notifyAll();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (Thread.currentThread().getName().equals("abc")) {

                }
            }

        }
    }
}
