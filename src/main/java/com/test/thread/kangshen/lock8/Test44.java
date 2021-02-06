package com.test.thread.kangshen.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8 锁 关于锁的8个问题
 * 一个静态同步方法，一个普通同步方法
 * 一个锁的是Class，一个锁的调用者
 */
public class Test44 {
    public static void main(String[] args) {
        // 两个对象
        Phone44 phone1 = new Phone44();

        new Thread(()->{
            phone1.call();
        }, "B").start();
        new Thread(()->{
            phone1.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class Phone44 {

    // synchroniaed 锁的是方法的调用者
    // static 静态方法， synchroniaed 锁的是class
    public static synchronized void sendSms() {
       /* try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }

}
