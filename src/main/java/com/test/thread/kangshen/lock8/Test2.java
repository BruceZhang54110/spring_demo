package com.test.thread.kangshen.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8 锁 关于锁的8个问题
 * 总是先 sendSms 后 call，因为 A 线程先拿到锁
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
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

class Phone2 {

    // synchroniaed 锁的是方法的调用者
    // 两个方法用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized void call() {
        System.out.println("call");
    }

    // 这里没有锁，不是同步方法，不受锁 影响
    public void hello() {
        System.out.println("hello");
    }
}
