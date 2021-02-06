package com.test.thread.kangshen.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 要用线程操作资源类
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 多线程操作同一個资源类
        Ticket2 ticket = new Ticket2();
        new Thread(()->{
            for(int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        /*new Thread(()->{
            for(int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();*/
    }


}

class Ticket2 {
    //
    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock(); // 加锁
        try {
            // 业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出票，剩余：" + number-- );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }

    }
}
