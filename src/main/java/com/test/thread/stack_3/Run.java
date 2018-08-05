package com.test.thread.stack_3;
/**
 * 多生产者，一个消费者，操作栈
 * 使用 if 判断是否可以消费，导致消费者被唤醒后，直接执行if 语句块后边的代码，而不会判断是否可以消费。导致发生list 下标越界异常 
 * 要改成 使用 while 进行判断，就可以避免 ，在 stack_2_new 中是修改后的代码,
 *  当然为了，避免假死要使用 notifyAll
 * @author Administrator
 *
 */
public class Run {

	
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		
		Product p = new Product(myStack);
		Product p1 = new Product(myStack);
		Product p2 = new Product(myStack);
		Product p3 = new Product(myStack);
		Product p4 = new Product(myStack);
		Product p5 = new Product(myStack);
		
		Consumer c = new Consumer(myStack);
		
		ProductThread pt = new ProductThread(p);
		ProductThread pt1 = new ProductThread(p1);
		ProductThread pt2 = new ProductThread(p2);
		ProductThread pt3 = new ProductThread(p3);
		ProductThread pt4 = new ProductThread(p4);
		ProductThread pt5 = new ProductThread(p5);
		
		ConsumerThread ct = new ConsumerThread(c);
		ct.setName("ct");
		
		
		
		pt.start();
		pt1.start();
		pt2.start();
		pt3.start();
		pt4.start();
		pt5.start();
		ct.start();
		
	}
}
