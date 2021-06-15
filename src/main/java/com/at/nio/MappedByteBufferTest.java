package com.at.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * 1. MappedByteBuffer可以让文件直接在内存(堆外内存)中修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        // 获取对应的文件通道
        FileChannel randomAccessFileChannel = randomAccessFile.getChannel();
        // 参数： 读写模式, 起始位置, 映射到内存的大小(将文件的多少个字节映射到内存)
        MappedByteBuffer map = randomAccessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0, (byte)'H');
        map.put(5, (byte) '9');
        randomAccessFileChannel.close();
        System.out.println("修改成功");

    }
}
