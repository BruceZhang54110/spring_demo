package com.test.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程顺序打印ABC
 */
public class ThreadPrint2 {
    private static volatile int state = 0;
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final Condition conditionA = reentrantLock.newCondition();
    private static final Condition conditionB = reentrantLock.newCondition();
    private static final Condition conditionC = reentrantLock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            reentrantLock.lock();
            try {
                boolean b = state % 3 == 0;
                if (!b) {
                    conditionA.await();
                }
                System.out.println("A");
                state++;
                conditionB.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-A");
        Thread threadB = new Thread(() -> {
            reentrantLock.lock();
            try {
                boolean b = state % 3 == 1;
                if (!b) {
                    conditionB.await();

                }
                System.out.println("B");
                state++;
                    conditionC.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-B");
        Thread threadC = new Thread(() -> {
            reentrantLock.lock();
            try {
                boolean b = state % 3 == 2;
                if (!b) {
                    conditionC.await();
                }
                System.out.println("C");
                state++;
                conditionA.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }, "Thread-C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
