package com.niozuse.channeltest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 使用FileChannel 写入字符串到文件
 */
public class NioFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello world";
        // 创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhw/myDevelop/testChannel.txt");
        // 通过输出流获取对应的文件Channel
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将Str写入到bytebuffer
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));

        // 反转
        byteBuffer.flip();
        // 缓冲区数据写入到channel
        fileOutputStreamChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}
