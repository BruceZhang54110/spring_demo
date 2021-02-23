package com.test.thread.mianshi;

public class PrintABCUsingWaitNotify {
    private int state;
    private int times;

    private Object LOCK = new Object();

    public PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        for (int i = 0; i < times;) {
            synchronized (LOCK) {
                if (state % 3 == targetNum) {
                    state++;
                    i++;
                    System.out.print(name);
                    LOCK.notifyAll(); // 会让所有处于等待池中的线程会全部进入到锁池去竞争获取锁的机会

                }
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify printABCUsingWaitNotify = new PrintABCUsingWaitNotify(3);
        new Thread(()->{
            printABCUsingWaitNotify.printLetter("B",1);
        }).start();
        new Thread(()->{
            printABCUsingWaitNotify.printLetter("A",0);
        }).start();
        new Thread(()->{
            printABCUsingWaitNotify.printLetter("C",2);
        }).start();
    }
}
