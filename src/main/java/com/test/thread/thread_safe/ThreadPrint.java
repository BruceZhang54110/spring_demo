package com.test.thread.thread_safe;

public class ThreadPrint {
    private  static int state = 0;
    private static String lock = "1";

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                while (state % 3 == 0) {
                    System.out.println("A");
                    state++;
                }
            }
        }).start();
        new Thread(()->{
            synchronized (lock) {
                while (state % 3 == 1) {
                    System.out.println("B");
                    state++;
                }
            }
        }).start();
        new Thread(()->{
            synchronized (lock) {
                while (state % 3 == 2) {
                    System.out.println("C");
                    state++;
                }
            }
        }).start();
    }
}
