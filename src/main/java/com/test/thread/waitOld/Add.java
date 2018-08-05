package com.test.thread.waitOld;

public class Add {
	
	private String lock;

	public Add(String lock) {
		super();
		this.lock = lock;	}
	
	public void add() {
		synchronized (lock) {
		}
	}

	
}
