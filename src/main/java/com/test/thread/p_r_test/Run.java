package com.test.thread.p_r_test;

/**
 * 生产者消费者
 * @author Administrator
 *
 */
public class Run {

	public static void main(String[] args) {
		String lock = new String("");
		P p = new P(lock);
		C r = new C(lock);
		
		ThreadP pThread = new ThreadP(p);
		ThreadC cThread = new ThreadC(r);
		pThread.start();
		cThread.start();

	}
}
