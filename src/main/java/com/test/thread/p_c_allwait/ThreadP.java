package com.test.thread.p_c_allwait;

public class ThreadP extends Thread {

	private P p;

	public ThreadP(P p) {
		super();
		this.p = p;
	}
	
	@Override
	public void run() {
		while (true) {
			p.setValue();
		}

	}
	
	
}
