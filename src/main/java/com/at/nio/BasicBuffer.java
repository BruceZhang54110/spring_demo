package com.at.nio;
import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        // 举例说明Buffer 的使用
        // 创建一个Buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        // 从Buffer读取数据
        intBuffer.flip(); // 将Buffer转换（读写切换）

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
