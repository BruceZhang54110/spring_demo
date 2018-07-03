package com.test.suspend_resume_deal_lock;

public class SynchroizedObject {

	synchronized public void printString() {
		System.out.println("begin");
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("a 线程永远 suspend了 !");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}
