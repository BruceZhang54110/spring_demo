package com.test.thread.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 1. implements Lock
 * 2. 写一个静态的内部类，extends AbstractQueuedSynchronizer
 */
public class CreateLock implements Lock {

    private Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
        // 3. 重写AQS里的方法，达到我们的预期,AQS 只有5个方法可以重写

        /**
         * 排它锁加锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (!Thread.currentThread().getName().startsWith("1-")) {
                return false;
            }
            if (compareAndSetState(0, arg)) {
                // 加锁成功
                setExclusiveOwnerThread(Thread.currentThread());// 领证结婚
                return true;
            }
            // 加锁失败
            return false;
        }

        /**
         * 排它锁释放锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                // 压根没锁
                throw new IllegalMonitorStateException();
            }
            setState(0);
            return false;
        }

        Condition getCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }
    @Override
    public Condition newCondition() {
        return null;
    }
}
