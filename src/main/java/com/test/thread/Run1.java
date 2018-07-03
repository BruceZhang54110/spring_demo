package com.test.thread;

/**
 * 不共享数据
 * @author Administrator
 *
 */
public class Run1 {

	public static void main(String[] args) {
		MyThread2 a = new MyThread2("A");
		MyThread2 b = new MyThread2("B");
		MyThread2 c = new MyThread2("C");
		a.start();
		b.start();
		c.start();
	}
}
