package com.test.thread.stack_2_new;
/**
 * 消费者线程
 * @author Administrator
 *
 */
public class ConsumerThread extends Thread {

	private Consumer c;

	public ConsumerThread(Consumer c) {
		super();
		this.c = c;
	}
	
	@Override
	public void run() {
		while (true) {
			c.popService();
		}
	}
}
