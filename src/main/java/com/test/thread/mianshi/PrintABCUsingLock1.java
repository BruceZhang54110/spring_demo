package com.test.thread.mianshi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 *
 */
public class PrintABCUsingLock1 {
    private int times;// 控制打印次数
    private int state = 0; // 当前状态值，保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();

    public PrintABCUsingLock1(int times) {
        this.times = times;
    }
    private void printLetter(String name, int targetNum) {
        //System.out.println("name :" + name + ", thread: " + Thread.currentThread().getName());
        for (int i = 0;i < times;) {
            //System.out.println("name :" + name + ", thread: " + Thread.currentThread().getName() + ", i : " + i );

            lock.lock();
            //System.out.println("lock success :" + Thread.currentThread().getName());
            if (state % 3 == targetNum) {

                state++;
                // 这里是关键，将i++ 放入到if中，根据我们想要打印ABC的顺序，只有第一次打印A的线程成功获取到锁，并且打印成功。才会i++
                // i++ 成功
                // 如果A一直没有打印，则负责打印B和打印C的线程则一直重复走for循环，一直去尝试获取锁
                i++;
                System.out.print(name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock1 loopThread = new PrintABCUsingLock1(2);
        new Thread(()->{
            loopThread.printLetter("B", 1);
        }).start();
        new Thread(()->{
            loopThread.printLetter("A", 0);
        }).start();
        new Thread(()->{
            loopThread.printLetter("C", 2);
        }).start();
    }
}
