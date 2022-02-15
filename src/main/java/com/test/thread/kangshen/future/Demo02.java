package com.test.thread.kangshen.future;

import java.util.concurrent.*;

public class Demo02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final Future<String> submit = executorService.submit(new CallableTask());
        System.out.println(submit.get());
        System.out.println("获取结果结束");
        executorService.shutdown();
    }

    static class CallableTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("正在执行call()");
            TimeUnit.SECONDS.sleep(5);
            return "执行 call() ...";
        }
    }

}
