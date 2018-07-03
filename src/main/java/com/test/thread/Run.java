package com.test.thread;

/**
 * 使用多线程技术时，代码的运行结果一代码执行顺序或调用顺序无关
 * @author Administrator
 *
 */
public class Run {

	public static void main(String[] args) {
//		Mythread mythread = new Mythread();
//		mythread.start();
		Runnable runnable = new MyRunnable();
		Thread thread = new Thread(runnable);
		thread.start();
		System.out.println("运行结束");
	}
}
