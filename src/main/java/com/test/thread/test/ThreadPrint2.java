package com.test.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程顺序打印ABC
 */
public class ThreadPrint2 {
    private volatile static int state = 0;
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                reentrantLock.lock();
                while (state % 3 == 0) {
                    System.out.println("A");
                    state++;
                }
            } finally {
                reentrantLock.unlock();
            }
        }).start();
        new Thread(()->{
            try {
                reentrantLock.lock();
                while (state % 3 == 1) {
                    System.out.println("B");
                    state++;
                }
            } finally {
                reentrantLock.unlock();
            }
        }).start();
        new Thread(()->{
            try {
                reentrantLock.lock();
                while (state % 3 == 2) {
                    System.out.println("C");
                    state++;
                }
            } finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}
