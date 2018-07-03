package com.test.thread.youxianji;

public class Mythread2 extends Thread {

	public void run() {
		System.out.println("MyThread2 run priority=" + this.getPriority());
		
	}
}
