package com.test.thread.stack_2_new;
/**
 * 消费者
 * @author Administrator
 *
 */
public class Consumer {

	private MyStack myStack;

	public Consumer(MyStack myStack) {
		super();
		this.myStack = myStack;
	}
	
	public void popService() {
		System.out.println("pop= " + myStack.pop());
	}
}
