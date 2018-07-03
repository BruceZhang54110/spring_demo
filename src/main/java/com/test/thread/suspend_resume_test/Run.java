package com.test.thread.suspend_resume_test;

public class Run {
	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(5000);
			//A段
			thread.suspend();//暂停线程
			System.out.println("A=" + System.currentTimeMillis() + " i=" + thread.getI());
			Thread.sleep(5000);
			System.out.println("A=" + System.currentTimeMillis() + " i=" + thread.getI());
			//B段
			thread.resume();//恢复线程\
			Thread.sleep(5000);
			//C段
			thread.suspend();//暂停线程
			System.out.println("B=" + System.currentTimeMillis() + " i=" + thread.getI());
			Thread.sleep(5000);
			System.out.println("B=" + System.currentTimeMillis() + " i=" + thread.getI());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
