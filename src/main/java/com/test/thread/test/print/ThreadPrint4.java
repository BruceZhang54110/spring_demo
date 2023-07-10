package com.test.thread.test.print;

public class ThreadPrint4 {
    private static String stateStr = "A";
    private static int countA = 0;
    private static int countB = 0;
    private static String lock = "1";


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (countA < 50) {
                synchronized (lock) {
                    if ("A".equals(stateStr)) {
                        countA++;
                        System.out.println("A, countA: " + countA);
                        stateStr = "B";
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        Thread thread2 = new Thread(() -> {
            while (countB < 50) {
                synchronized (lock) {
                    if ("B".equals(stateStr)) {
                        countB++;
                        System.out.println("B, countB:" + countB);
                        stateStr = "A";
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        thread1.start();
        thread2.start();
    }
}
