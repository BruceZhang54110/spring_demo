package com.test.thread.thread_safe;

public class BLogin extends Thread {

	@Override
	public void run() {
		LoginServlet.doPost("b", "bb");
	}
}
