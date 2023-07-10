package com.test.thread.volatiletest;

import java.util.concurrent.TimeUnit;

public class Test {


    public static void main(String[] args) throws InterruptedException {
        ValueTest valueTest = new ValueTest();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            valueTest.numAdd();
            System.out.println("子线程 end：num：" + valueTest.num);
        }, "子线程").start();

        System.out.println("valueTest.num:" + valueTest.num);
        long count = 0;
        while (valueTest.num == 0) {
            count++;
            //if (count == 1) {
                //System.out.println("主线程循环判断num：" + valueTest.num);
            //}
        }
        if (valueTest.num != 0) {
            System.out.println("count:" + count);
        }
        System.out.println("程序结束, num:" + valueTest.num);

    }
}

class ValueTest {
    int num = 0;

    public void numAdd() {
        this.num += 100;
    }
}
