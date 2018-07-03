package com.test.thread;

public class Test1 {

	public static void main(String[] args) {
		try {
			MyThread1 mythread1 = new MyThread1();
			mythread1.setName("mythread1");
			mythread1.start();
			
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("main=" + Thread.currentThread().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
