package com.test.thread.t9;

public class Mythread1 extends Thread {

	private MyOneList list;
	
	public Mythread1(MyOneList list) {
		super();
		this.list = list;
	}
	
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "A");
	}
	
	
}
