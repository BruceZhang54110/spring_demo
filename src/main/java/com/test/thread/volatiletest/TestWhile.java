package com.test.thread.volatiletest;

public class TestWhile {
    public static void main(String[] args) {
        ValueTest1 valueTest1 = new ValueTest1();

        long count = 0;
        while (true) {
            if (count == 0) {
                System.out.println("count:" + count);
            }
            count = count + 1;
           // System.out.println("end count:" + count);
        }
       // System.out.println("程序结束, num:" + valueTest1.num);
    }
}

class ValueTest1 {
    public int num = 0;

    public void numAdd() {
        this.num += 100;
    }
}