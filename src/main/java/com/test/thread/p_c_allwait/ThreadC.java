package com.test.thread.p_c_allwait;

public class ThreadC extends Thread {

	private C r;

	public ThreadC(C r) {
		super();
		this.r = r;
	}
	
	@Override
	public void run() {
		while (true) {
			r.getValue();
		}

	}
	
	
}
