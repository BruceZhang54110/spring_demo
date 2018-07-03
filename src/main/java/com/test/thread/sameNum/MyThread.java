package com.test.thread.sameNum;

public class MyThread extends Thread {
	private int i = 5;
	
	@Override
	synchronized public void run() {
		System.out.println("i=" + (i--) + "threadname=" + Thread.currentThread().getName());
	}
	

}
