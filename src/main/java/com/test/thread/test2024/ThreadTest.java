package com.test.thread.test2024;

public class ThreadTest {

    public static void main(String[] args) {
        String name = "111";
        new Thread(new Task(name)).start();
    }

    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Thread:" + this.name);

        }
    }
}
