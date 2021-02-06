package com.test.thread.kangshen.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8 锁 关于锁的8个问题
 * 总是先 sendSms 后 call，因为 A 线程先拿到锁
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone.call();
        }, "B").start();
    }


}

class Phone {

    // synchroniaed 锁的是方法的调用者
    // 两个方法用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms() {
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }
}
