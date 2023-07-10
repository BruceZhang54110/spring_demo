package com.test.thread.kangshen.ticket.pc;

/**
 * 线程间通信问题，生产者和消费者问题
 * 线程交替执行 A B 操作同一个变量 num=0
 * A num+1
 * B num-1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
           for (int i=0 ;i < 100;i++) {
               try {
                   data.increment();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"A").start();

        new Thread(()->{
            for (int i=0 ;i < 10;i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
       new Thread(()->{
            for (int i=0 ;i < 90;i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
       /* new Thread(()->{
            for (int i=0 ;i < 10;i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();*/
    }
}

class Data {
    private volatile int number = 0;

    /**
     * 模拟生产消息
     * @throws InterruptedException
     */
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=> number: " + number);
        // 通知其他线程，我生产完毕了
        this.notifyAll();
    }

    /**
     * 模拟消费消息
     * @throws InterruptedException
     */
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=> number: " + number);
        // 通知其他线程，我消费完毕了
        this.notifyAll();
    }
}
