package com.test.thread.cl.at;

import java.util.concurrent.atomic.AtomicInteger;

public class AutomicTest {
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ai.incrementAndGet();
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 7; i++) {
                ai.incrementAndGet();
            }
        });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(ai.get());
    }

}
