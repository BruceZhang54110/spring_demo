package com.test.thread.imooc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		// 创建线程池
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		// 向线程池中提交任务
		Future<String> future = newCachedThreadPool.submit(new MyCallable());
		if (!future.isDone()) {
			System.out.println("task has not finished please wait.");
		} else {
			System.out.println("ssssssssssssssss");
		}
		System.out.println("wwwwwwwwwwwwwww");
		try {
			System.out.println("task return: " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			newCachedThreadPool.shutdown();
		}
	}
}
