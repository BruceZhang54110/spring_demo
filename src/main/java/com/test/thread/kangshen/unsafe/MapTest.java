package com.test.thread.kangshen.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentModificationException
 * 并发修改异常
 */
public class MapTest {
    public static void main(String[] args) {
        // Map<String, Object> map = new HashMap<>();
        // 加载因子、初始容量
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }).start();
        }
    }
}
