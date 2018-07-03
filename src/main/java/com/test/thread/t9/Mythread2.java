package com.test.thread.t9;

public class Mythread2 extends Thread {

	private MyOneList list;
	
	public Mythread2(MyOneList list) {
		super();
		this.list = list;
	}
	
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "B");
	}
	
	
}
