package com.test.thread.t9;

/**
 * 脏读
 * @author Administrator
 *
 */
public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyOneList list = new MyOneList();
		Mythread1 thread1 = new Mythread1(list);
		thread1.setName("A");
		thread1.start();
		
		Mythread2 thread2 = new Mythread2(list);
		thread2.setName("B");
		thread2.start();
		Thread.sleep(8000);
		System.out.println("listSize=" + list.getSize());
	}
}
