package com.test.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个buffer，长度为5
        IntBuffer intBuffer = IntBuffer.allocate(5);
        /*intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);*/

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 读取数据
        // 读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }
}
