package com.test.thread.mylock;

import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
    }
}
