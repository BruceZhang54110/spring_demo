package com.test.thread.stack_1;
/**
 * 一个生产者，一个消费者,操作栈
 * @author Administrator
 *
 */
public class Run {

	
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		
		Product p = new Product(myStack);
		Consumer c = new Consumer(myStack);
		
		ProductThread pt = new ProductThread(p);
		ConsumerThread ct = new ConsumerThread(c);
		
		pt.start();
		ct.start();
	}
}
