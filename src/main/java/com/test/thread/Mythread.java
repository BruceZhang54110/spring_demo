package com.test.thread;

public class Mythread extends Thread {

	@Override
	public void run() {
		super.run();
		System.out.println("Mythread");
	}
}
