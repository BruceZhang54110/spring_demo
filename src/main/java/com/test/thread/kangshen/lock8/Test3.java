package com.test.thread.kangshen.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8 锁 关于锁的8个问题
 * 总是先 sendSms 后 call，因为 A 线程先拿到锁
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(()->{
            phone1.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone2.call();
        }, "B").start();
    }


}

class Phone3 {

    // synchroniaed 锁的是方法的调用者
    // static 静态方法， synchroniaed 锁的是class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public static synchronized void call() {
        System.out.println("call");
    }

}
