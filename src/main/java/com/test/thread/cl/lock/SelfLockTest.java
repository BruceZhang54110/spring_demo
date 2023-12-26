package com.test.thread.cl.lock;

import java.util.concurrent.locks.Lock;

public class SelfLockTest {

    public static Lock lock = new SelfLock();
    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            testLock();
        });
        Thread b = new Thread(() -> testLock());
        a.setName("a 线程");
        b.setName("b 线程");

        a.start();
        b.start();

    }

    public static void testLock() {
        lock.lock();
        try {
            System.out.println("获取到锁了，当前线程是" + Thread.currentThread().getName());
            while (true) {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}
