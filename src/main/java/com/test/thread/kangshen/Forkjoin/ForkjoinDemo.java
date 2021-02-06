package com.test.thread.kangshen.Forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool
 * ForkJoinTask
 */
public class ForkjoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long temp = 10000L;

    public ForkjoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for(Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;

        } else {
            long middle  = (start +end) /2;
            ForkjoinDemo task1 = new ForkjoinDemo(start ,middle);
            task1.fork(); // 将任务压入线程队列
            ForkjoinDemo task2 = new ForkjoinDemo(middle+1 ,end);
            task2.fork();
            return task1.join() + task2.join();

        }
    }
}
