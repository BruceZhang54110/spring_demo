package com.test.thread.shouhu;

public class Run {

	public static void main(String[] args) {
		try {
			Mythread thread = new Mythread();
			thread.setDaemon(true);
			thread.start();
			Thread.sleep(5000);
			System.out.println("我离开了thread对象也不再打印了,也就是停止了");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
