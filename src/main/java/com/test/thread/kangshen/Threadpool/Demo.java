package com.test.thread.kangshen.Threadpool;

import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 1; i <= 9; i++) {
            threadPoolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName() + " ok");
            });
        }
        threadPoolExecutor.shutdown();
    }
}
