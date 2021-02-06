package com.test.thread.kangshen.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        // 队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println("element :" + arrayBlockingQueue.element());
        // System.out.println(arrayBlockingQueue.add("d")); //java.lang.IllegalStateException: Queue full
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove()); //Exception in thread "main" java.util.NoSuchElementException
    }

    public static void test2() {
        // 队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
    }


    /**
     * 阻塞等待
     */
    public static void test3() {
        // 队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        try {
            arrayBlockingQueue.put("a");
            arrayBlockingQueue.put("b");
            arrayBlockingQueue.put("c");
            //arrayBlockingQueue.put("d");
            System.out.println("------结束-----");
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take()); // 没有这个元素，一直等待

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞等待
     */
    public static void test4() {
        // 队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        try {
            arrayBlockingQueue.offer("a");
            arrayBlockingQueue.offer("b");
            arrayBlockingQueue.offer("c");
            arrayBlockingQueue.offer("d", 2,  TimeUnit.SECONDS);
            System.out.println("------结束-----");
            System.out.println(arrayBlockingQueue.poll());
            System.out.println(arrayBlockingQueue.poll());
            System.out.println(arrayBlockingQueue.poll());
            System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
