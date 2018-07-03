package com.test.suspend_resume_deal_lock;

/**
 * 因为线程a暂停了,导致其他线程无法访问公共同步对象.
 * @author Administrator
 *
 */
public class Run {

	public static void main(String[] args) {
		try {
			final SynchroizedObject object = new SynchroizedObject();
			Thread thread1 = new Thread() {
				@Override
				public void run() {
					object.printString();
				}
			};
			thread1.setName("a");
			thread1.start();
			Thread.sleep(1000);
			
			Thread thread2 = new Thread() {
				@Override
				public void run() {
					System.out.println("thread2启动了,但是进入不了printString() 方法!,只打印一个begin");
					System.out.println("因为printString() 方法被a线程锁定并永远suspend暂停掉了!");
					object.printString();
				}
			};
			thread2.start();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
