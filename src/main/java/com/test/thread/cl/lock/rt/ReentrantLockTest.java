package com.test.thread.cl.lock.rt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        /**
         * AQS-compareAndSetState
         * acquire
         * addWaiter
         * enq
         * acquireQueued
         * shouldParkAfterFailedAcquire
         * parkAndCheckInterrupt
         * cancelAcquire
         */
        // acquire方法是AQS提供的模板方法，是为了进行锁的获取；
        // tryAcquire方法是AQS提供的可以复写的方法，主要是完成加加锁，状态变化的逻辑（state）；
        // addWaiter 将获取失败的线程放到同步队列里；
        // enq 如果 addWaiter 第一次没有成功就进行死循环添加；
        // acquireQueued 通过循环的自我检查，如果当前节点的前置节点是头结点，那么久尝试获取锁
        // ，如果不是头结点就调用 shouldParkAfterFailedAcquire方法，判断pred节点是不是SIGNAL 状态，如果是SIGNAL 状态那就好好的等着；
        // 如果是CANCELL 状态，就移除CANCELL的节点，其他状态的节点会通过CAS操作，替换为SIGNAL 状态。
        lock.lock();

        /**
         * 超时获取锁
         * 可以被中断
         */
        lock.tryLock(1, TimeUnit.SECONDS);

        /**
         * 支持中断的加锁
         * 和tryLock(1, TimeUnit.SECONDS) 的区别，相同点：都支持中断，不同点：前者既支持超时也支持超时时间内的中断
         */
        lock.lockInterruptibly();

        lock.isHeldByCurrentThread();

        /**
         * unparkSuccessor 唤醒后面的节点
         *
         * -------------
         * 调通tryRealease 知道释放掉所有的锁，state = 0, 因为考虑重入的情况，然后唤醒后继结点的线程让它进行锁竞争。
         */
        lock.unlock();
    }
}
