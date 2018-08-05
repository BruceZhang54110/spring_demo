package com.test.thread.stack_2_new;

import java.util.ArrayList;
import java.util.List;

public class MyStack {

	private List<String> list = new ArrayList<String>();
	
	/**
	 * 入
	 */
	synchronized public void push() {
		try {
			while (list.size() == 1) {
				this.wait();
			}
			list.add("anything = " + Math.random());
			this.notifyAll();
			System.out.println("push=" +list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 出
     * @return
     */
	synchronized public String pop() {
		String returnValue = "";
		try {
			while (list.size() == 0) {
				System.out.println("pop操作中：" +Thread.currentThread().getName() + " 线程是wait状态.");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop= " + list.size() + " , 线程名：" + Thread.currentThread().getName());
			
		} catch (Exception e) {
			System.out.println("================线程：" + Thread.currentThread().getName());
			e.printStackTrace();
		}
		return returnValue;
	}
}
