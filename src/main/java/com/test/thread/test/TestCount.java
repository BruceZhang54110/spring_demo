package com.test.thread.test;

import com.test.thread.Run;

public class TestCount {

    private static int count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(TestCount::add).start();
        }


    }

    private static synchronized void add() {
        count++;
    }
}
