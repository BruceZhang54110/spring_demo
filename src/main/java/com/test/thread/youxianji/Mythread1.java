package com.test.thread.youxianji;


public class Mythread1 extends Thread {

	public void run() {
		System.out.println("MyThread1 run priority=" + this.getPriority());
		Mythread2 thread2 = new Mythread2();
		thread2.start();
		
	}
}
