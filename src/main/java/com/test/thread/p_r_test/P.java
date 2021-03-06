package com.test.thread.p_r_test;

public class P {

	private String lock;

	public P(String lock) {
		super();
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				if (!"".equals(ValueObject.value)) {
					lock.wait();
				}
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println("set 的值是：" + value);
				ValueObject.value = value;
				lock.notify();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
}
