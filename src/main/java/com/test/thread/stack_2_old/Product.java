package com.test.thread.stack_2_old;
/**
 * 生产者
 * @author Administrator
 *
 */
public class Product {

	private MyStack myStack;

	public Product(MyStack myStack) {
		super();
		this.myStack = myStack;
	}
	
	public void pushService() {
		myStack.push();
	}
}
