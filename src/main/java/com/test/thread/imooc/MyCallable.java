
package com.test.thread.imooc;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		String value = "test";
		System.out.println("Ready to work");
		TimeUnit.SECONDS.sleep(10);
		System.out.println("task done");
		return value;
	}

}
