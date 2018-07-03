package com.test.thread.t9;

import java.util.ArrayList;
import java.util.List;

public class MyOneList {

	private List<String> list = new ArrayList();
	
	synchronized public void add(String data) throws InterruptedException {
		System.out.println("MyOneList-add: begin" + Thread.currentThread().getName());
		Thread.sleep(2000);
		list.add(data);
		System.out.println("MyOneList-add: end" + Thread.currentThread().getName());
	}
	
	synchronized public int getSize() throws InterruptedException {
		System.out.println("MyOneList-getSize: begin" + Thread.currentThread().getName());
		Thread.sleep(2000);
		System.out.println("MyOneList-getSize: end" + Thread.currentThread().getName());
		return list.size();
	}
	
	
}
