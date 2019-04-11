package com.test.thread.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mute implements Lock {
	
	private static class sync extends AbstractQueuedSynchronizer{
		// 当状态为0的是获取锁
		@Override
		protected boolean tryAcquire(int arg) {
			// 如果经过CAS设置状态成功(同步状态设置为1)，则代表获取了同步状态
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		@Override
		protected boolean tryRelease(int arg) {
			if (getState() == 0) {
				throw new IllegalMonitorStateException();
			}
			// 将同步状态重置为0.
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		@Override
		protected boolean isHeldExclusively() {
			// TODO Auto-generated method stub
			return getState() == 1;
		}
		
		Condition newCondition() {
			return new ConditionObject();
			
		}

	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
