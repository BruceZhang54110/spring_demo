package com.test.thread.cl.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 加锁
         * @param acquires the acquire argument. This value is always the one
         *        passed to an acquire method, or is the value saved on entry
         *        to a condition wait.  The value is otherwise uninterpreted
         *        and can represent anything you like.
         * @return
         */
        public boolean tryAcquire(int acquires) {
            /**
             * CAS操作
             */
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 解锁
         * @param arg the release argument. This value is always the one
         *        passed to a release method, or the current state value upon
         *        entry to a condition wait.  The value is otherwise
         *        uninterpreted and can represent anything you like.
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(0);
            return false;
        }

        /**
         * 创建 Condition
         */
        Condition newCondition() {
            return new ConditionObject();
        }


        public boolean isLocked() {

            return getState() == 1;
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        // 这里使用了模板方法模式
        sync.acquire(1);
    }
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);

    }
    @Override
    public void unlock() {
        sync.release(1);
    }
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isLocked();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
