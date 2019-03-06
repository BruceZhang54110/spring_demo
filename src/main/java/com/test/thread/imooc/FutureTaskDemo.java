package com.test.thread.imooc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
		new Thread(futureTask).start();
		if (!futureTask.isDone()) {
			System.out.println("task has not finished please wait.");
		}
		System.out.println("task return: " + futureTask.get());
	}
	
}
