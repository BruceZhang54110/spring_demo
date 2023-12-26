package com.test.free;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new CustomizableThreadFactory("custom-Callback"));

        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
            System.out.println("task run");
            TimeUnit.SECONDS.sleep(1);
            return "success";
        });
        ExecutorService listenerExecutor = Executors.newFixedThreadPool(10, new CustomizableThreadFactory("custom-Callback"));
        CountDownLatch countDownLatch = new CountDownLatch(3);
        listenableFutureTask.addListener(() -> {
            countDownLatch.countDown();
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("a");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread.interrupted();
        }, listenerExecutor);
        listenableFutureTask.addListener(() -> {
            System.out.println("b");
            countDownLatch.countDown();
        }, listenerExecutor);
        listenableFutureTask.addListener(() -> {
            System.out.println("c");
            countDownLatch.countDown();
        }, listenerExecutor);
        listenableFutureTask.addListener(() -> {
            System.out.println("d");
            countDownLatch.countDown();
        }, listenerExecutor);

        executorService.submit(listenableFutureTask);
        String s = listenableFutureTask.get();
        System.out.println("result:" + s);
        countDownLatch.await();
        executorService.shutdown();
        listenerExecutor.shutdown();

    }
}
