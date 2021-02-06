package com.test.thread.kangshen.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchhronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> objects = new SynchronousQueue<String>();// 同步队列

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                objects.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                objects.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                objects.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " 取出:  " +objects.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " 取出:  " +objects.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " 取出:  " +objects.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T2").start();
    }
}
