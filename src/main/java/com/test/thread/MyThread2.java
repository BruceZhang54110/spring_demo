package com.test.thread;

public class MyThread2 extends Thread {

	private int count = 5;
	public MyThread2(String name) {
		this.setName(name);//设置线程名字
	}
	
	@Override
	public void run() {
		super.run();
		while (count > 0) {
			count --;
			System.out.println("由 " + this.currentThread().getName() + "计算count=" + count);
		}
	}
}
