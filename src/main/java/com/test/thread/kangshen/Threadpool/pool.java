package com.test.thread.kangshen.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class pool {
    public static void main(String[] args) {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);// 固定线程大小的线程池
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 单个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
    }

}
