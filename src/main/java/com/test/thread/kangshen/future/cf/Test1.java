package com.test.thread.kangshen.future.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(()->{
            System.out.println("run ");
        }, executorService);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplier");
            System.out.println(1/0);
            return "supplyAsync";
        }, executorService);

       /* try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("exception: " + e.getMessage());
            e.printStackTrace();
        }*/

        completableFuture.join();
    }
}
