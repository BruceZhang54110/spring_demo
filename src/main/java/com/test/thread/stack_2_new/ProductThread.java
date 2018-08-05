package com.test.thread.stack_2_new;
/**
 * 生产者线程
 * @author Administrator
 *
 */
public class ProductThread extends Thread {

	private Product p;

	public ProductThread(Product p) {
		super();
		this.p = p;
	}
	
	@Override
	public void run() {
		while (true) {
			p.pushService();
		}
	}
}
