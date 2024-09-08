package com.niozuse.channeltest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * 从文件中读取内容
 */
public class NioFileChannel02 {
    public static void main(String[] args) throws IOException {
        // 创流
        File file = new File("/Users/zhw/myDevelop/testChannel.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        fileInputStreamChannel.read(byteBuffer);
        byteBuffer.flip();

        // 将字节转字符串
        byte[] array = byteBuffer.array();
        System.out.println(new String(array));

        // 通过输出流获取对应的文件Channel

    }
}
