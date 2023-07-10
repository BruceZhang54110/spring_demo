package com.test.thread.volatiletest;

import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) {
        // 资源类
        ShareData myData = new ShareData();

        // 子线程 实现了Runnable接口的，lambda表达式
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改number的值
            myData.setNumberTo100();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);

        }, "子线程").start();

        while(myData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明子线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t 主线程感知到了 number 不等于 0");

        /**
         * 最后输出结果：
         * 子线程     come in
         * 子线程     update number value:100
         * 最后线程没有停止，并行没有输出"主线程知道了 number 不等于0"这句话，说明没有用volatile修饰的变量，变量的更新是不可见的
         */
    }
}

class ShareData {
    int number = 0;

    public void setNumberTo100() {
        this.number = 100;
    }
}
