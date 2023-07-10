package com.test.thread.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static CreateLock lock = new CreateLock();
    //static Lock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()-> {
            System.out.println("thread 1====== start");

            testLock();
           /* try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            while (true) {}
           // System.out.println("thread1 stop");
        }, "1-1");
        Thread thread2 = new Thread(()-> {
            System.out.println("thread 2====== start");
            testLock();
            System.out.println("thread2 stop");
        }, "1-2");
        thread1.start();
        //TimeUnit.SECONDS.sleep(2);
        thread2.start();
    }

    public static void testLock() {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("加锁success：threadName:" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
