package com.test.thread.p_r_test;

public class C {

	private String lock;

	public C(String lock) {
		super();
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				if ("".equals(ValueObject.value)) {
					lock.wait();
				}
				String value = ValueObject.value;
				System.out.println("get 的值是：" + value);
				ValueObject.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
}
