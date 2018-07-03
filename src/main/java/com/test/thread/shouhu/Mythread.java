package com.test.thread.shouhu;

public class Mythread extends Thread {

	private int i = 0;
	
	public void run() {
		try {
			while (true) {
				i ++;
				System.out.println("i=" + i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
