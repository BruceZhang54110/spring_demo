package com.test.thread.imooc;

import com.mysql.jdbc.TimeUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolDemo {

	private static final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<Runnable> runnables = new SynchronousQueue<>();
		System.out.println(runnables.size());

		new Thread(()-> {
			System.out.println("监听线程启动...");
			while (true) {
				if (newCachedThreadPool instanceof ThreadPoolExecutor) {
					ThreadPoolExecutor threadPool = (ThreadPoolExecutor) newCachedThreadPool;
					int corePoolSize = threadPool.getCorePoolSize();
					int poolSize = threadPool.getPoolSize();
					int maximumPoolSize = threadPool.getMaximumPoolSize();
					int activeCount = threadPool.getActiveCount();
					long completedTaskCount = threadPool.getCompletedTaskCount();
					int largestPoolSize = threadPool.getLargestPoolSize();
					long taskCount = threadPool.getTaskCount();
					System.out.println(
							"corePoolSize:" + corePoolSize
									+ ", poolSize:" + poolSize
									+ ", maximumPoolSize:" + maximumPoolSize
									+ ", activeCount:" + activeCount
									+ ", completedTaskCount:" + completedTaskCount
									+ ", largestPoolSize:" + largestPoolSize
									+ ", taskCount:" + taskCount);
				}
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

		}).start();

		// 创建线程池
		// 向线程池中提交任务
		Future<String> future = newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(1);
		Future<String> future1 = newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		Future<String> future2 = newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		Future<String> future3 = newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);

		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);

		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		newCachedThreadPool.submit(new MyCallable());
		TimeUnit.SECONDS.sleep(2);
		newCachedThreadPool.submit(new MyCallable());
		newCachedThreadPool.submit(new MyCallable());
		newCachedThreadPool.submit(new MyCallable());
		newCachedThreadPool.submit(new MyCallable());
		newCachedThreadPool.submit(new MyCallable());
		newCachedThreadPool.submit(new MyCallable());
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
