package com.test.thread.youxianji;

public class Run {

	public static void main(String[] args) {
		System.out.println("main thread begin priority=" + Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(6);
		System.out.println("main thread end priority=" + Thread.currentThread().getPriority());
		Mythread1 thread1 = new Mythread1();
		thread1.start();
	}
	
}
