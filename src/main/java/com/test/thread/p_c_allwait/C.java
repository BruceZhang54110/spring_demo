package com.test.thread.p_c_allwait;

public class C {

	private String lock;

	public C(String lock) {
		super();
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				while (ValueObject.value.equals("")) {
					System.out.println("消费者 " + Thread.currentThread().getName() + "WAITING 了 ※※");
					lock.wait();
				}
				System.out.println("消费者 " + Thread.currentThread().getName() + "RUNNING 了 ");
				ValueObject.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
