package com.test.thread.mianshi;

public class OddEvenPrinter {
    private Object monitor = new Object();
    private int count;
    private int start;

    public OddEvenPrinter(int initCount) {
        this.count = initCount;
    }
    private void print(){
        while(start <= count) {
            synchronized (monitor) {
                System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), start++));
                monitor.notifyAll();
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                monitor.notifyAll();
            }


        }

    }

    public static void main(String[] args) {
        OddEvenPrinter oddEvenPrinter = new OddEvenPrinter(100);
        new Thread(()->{
            oddEvenPrinter.print();
        }).start();
        new Thread(()->{
            oddEvenPrinter.print();
        }).start();

    }
}
