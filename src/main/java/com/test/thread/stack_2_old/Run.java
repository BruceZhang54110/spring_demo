package com.test.thread.stack_2_old;
/**
 * 一个生产者，多个消费者,操作栈
 * 使用 if 判断是否可以消费，导致消费者被唤醒后，直接执行if 语句块后边的代码，而不会判断是否可以消费。导致发生list 下标越界异常 
 * 要改成 使用 while 进行判断，就可以避免 ，在 stack_2_new 中是修改后的代码
 * @author Administrator
 *
 */
public class Run {

	
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		
		Product p = new Product(myStack);
		
		Consumer c = new Consumer(myStack);
		Consumer c1 = new Consumer(myStack);
		Consumer c2 = new Consumer(myStack);
		Consumer c3 = new Consumer(myStack);
		Consumer c4 = new Consumer(myStack);
		
		ProductThread pt = new ProductThread(p);
		
		ConsumerThread ct = new ConsumerThread(c);
		ct.setName("ct");
		
		ConsumerThread ct1 = new ConsumerThread(c1);
		ct1.setName("ct1");
		ConsumerThread ct2 = new ConsumerThread(c2);
		ct2.setName("ct2");
		ConsumerThread ct3 = new ConsumerThread(c3);
		ct3.setName("ct3");
		ConsumerThread ct4 = new ConsumerThread(c4);
		ct4.setName("ct4");
		
		
		pt.start();
		
		ct.start();
		ct1.start();
		ct2.start();
		ct3.start();
		ct4.start();
	}
}
