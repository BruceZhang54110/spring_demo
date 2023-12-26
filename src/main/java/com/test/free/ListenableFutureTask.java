package com.test.free;


import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {


    private ExecutionList executionList = new ExecutionList();

    public ListenableFutureTask(Callable<V> callable) {
        super(callable);
    }

    public ListenableFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        executionList.add(listener, executor);
    }

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<V>(callable);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, V result) {
        return new ListenableFutureTask<V>(runnable, result);
    }

    @Override
    protected void done() {
        executionList.execute();
    }

}
