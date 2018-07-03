package com.test.thread.t6;

public class Run2 {

	 public static void main(String[] args) {
		MyThread myThread = new MyThread();
		/**
		 * 构造方法的打印:main
run方法的打印:Thread-0

		 */
		//myThread.start();
		
		/**
		 * 
		 * 构造方法的打印:main
		 *	run方法的打印:main
		 */
		myThread.run();
	}
}
