package com.test.cc;

import org.apache.commons.io.IOUtils;

public class Test {

    public static void main(String[] args) {
        print3();
    }

    public static void print1() {
        int i = 0;
        for (int j = 0; j < 50; j++) {
            i = i++;
        }
        System.out.println("i=" + i);
    }

    public static void print2() {
        int i = 0;
        for (int j = 0; j < 50; j++) {
            i = ++i;
        }
        System.out.println("i=" + i);
    }

    public static void print3() {
        int i = 0;
        for (int j = 0; j < 2; j++) {
            i = (i++) + (++i);
            System.out.println("for i=" + i);
        }
        System.out.println("i=" + i);
    }
}
