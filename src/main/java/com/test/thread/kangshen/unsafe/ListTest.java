package com.test.thread.kangshen.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ConcurrentModificationException 并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
        // 并发下ArrayList是线程不安全的
        /**
         * 解决方法
         * 1、可以用Vector
         * 2、List<String> safeList = Collections.synchronizedList(new ArrayList<>());
         * 3、CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
         */

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
