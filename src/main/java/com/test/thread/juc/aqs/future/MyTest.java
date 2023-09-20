package com.test.thread.juc.aqs.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> future = executorService.submit(new Task1());

        // 添加异步回调
        executorService.execute(new CallbackListener<String>(future, executorService) {
            @Override
            void onSuccess() {
                System.out.println("回调success");
            }

            @Override
            void onFail() {
                System.out.println("回调fail");
            }
        });

        System.out.println("其他逻辑");



    }

    public static abstract class CallbackListener<T> implements Runnable{
        private Future<T> future;
        private ExecutorService executorService;
        abstract void onSuccess();
        abstract void onFail();

        public CallbackListener(Future<T> future, ExecutorService executorService) {
            this.future = future;
            this.executorService = executorService;
        }

        @Override
        public void run() {
            while (!future.isDone()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("还没执行完成");
                continue;
            }
            if (future.isDone()) {
                onSuccess();
            }
        }
    }


    /**
     * 异步任务
     */
    public static class Task1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("异步任务1");
                return "success";
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            return "end";
        }
    }
}
