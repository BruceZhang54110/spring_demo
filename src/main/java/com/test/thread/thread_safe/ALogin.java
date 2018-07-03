package com.test.thread.thread_safe;

public class ALogin extends Thread {

	@Override
	public void run() {
		
		LoginServlet.doPost("a", "aa");
		
	}
}
