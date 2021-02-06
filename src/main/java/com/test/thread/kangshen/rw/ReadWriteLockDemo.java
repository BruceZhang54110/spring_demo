package com.test.thread.kangshen.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写锁) 一次只能被一个线程占有
 * 共享锁(读锁) 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 共存
 * 读-写互斥
 * 写-写互斥
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCacheLock cache = new MyCacheLock();
        for (int i = 1 ;i <=5; i++) {
            final int temp = i;
            new Thread(()->{
                cache.put(temp+"", temp+"");
            }, String.valueOf(i)).start();
        }
        for (int i = 1 ;i <=5; i++) {
            final int temp = i;
            new Thread(()->{
                cache.get(temp+"");
            }, String.valueOf(i)).start();
        }

    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() +" 写入 " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() +" 写入OK");

    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() +" 读取 " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() +" 读取OK");

    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<String, Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() +" 写入 " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() +" 写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() +" 读取 " + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() +" 读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
