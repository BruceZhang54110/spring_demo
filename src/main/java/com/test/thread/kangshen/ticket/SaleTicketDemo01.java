package com.test.thread.kangshen.ticket;

/**
 * 要用线程操作资源类
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 多线程操作同一個资源类
        Ticket ticket = new Ticket();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(()->{
            for(int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "C").start();
    }


}

class Ticket {
    //
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出票，剩余：" + number-- );
        }
    }
}
