package com.test.thread.kangshen.Forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    /**
     * sum=500000000500000000, 耗时：5703
     */
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 0L ;i <= 20_0000_0000L; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", 耗时：" +(end-start));
    }

    /**
     * sum=500000000500000000, 耗时：4294
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkjoinDemo task = new ForkjoinDemo(0L, 20_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);// 提交任务
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", 耗时：" +(end-start));
    }

    /**
     * sum=500000000500000000, 耗时：160
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test3() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 20_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", 耗时：" +(end-start));
    }
}
