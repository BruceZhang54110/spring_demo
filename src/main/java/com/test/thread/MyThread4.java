package com.test.thread;

public class MyThread4 extends Thread {

	private int count = 5;
	public MyThread4(String name) {
		this.setName(name);//设置线程名字
	}
	

	public MyThread4() {
		// TODO Auto-generated constructor stub
	}


 public void run() {
		super.run();
			count--;
			System.out.println("由 " + this.currentThread().getName() + "计算,count=" + count);
	}
}
