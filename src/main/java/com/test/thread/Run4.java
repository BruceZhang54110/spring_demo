package com.test.thread;

public class Run4 {

	public static void main(String[] args) {
		MyThread4 myThread4 = new MyThread4();
		Thread a = new Thread(myThread4, "A");
		Thread b = new Thread(myThread4, "B");
		Thread c = new Thread(myThread4, "C");
		Thread d = new Thread(myThread4, "D");
		Thread e = new Thread(myThread4, "E");
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
		/*MyThread4 a = new MyThread4("A");
		MyThread4 b = new MyThread4("B");
		MyThread4 c = new MyThread4("C");
		MyThread4 d = new MyThread4("D");
		a.start();
		b.start();
		c.start();
		d.start();*/
	}
}
