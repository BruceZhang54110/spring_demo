package com.at.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 读取本地文件
 */
public class NIOFileChannel02{
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\file01.txt");
        FileInputStream fis = new FileInputStream(file);
        final FileChannel fileChannel = fis.getChannel();
        // 创建缓冲区
        final ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将通道数据读入到缓冲区
        fileChannel.read(byteBuffer);
        // 字节转为字符串
        System.out.println(new String(byteBuffer.array()));
        fis.close();

    }
}
