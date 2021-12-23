package com.at.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 字符车串写到本地文件
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {

        String str = "hello, world";
        // 创建输出流
        final FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        // 通过输出流获取对应的文件Channel
        final FileChannel channel = fileOutputStream.getChannel();
        // 创建缓冲区ByteBuffer
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str 放到ByteBuffer
        byteBuffer.put(str.getBytes());

        // 对ByteBuffer反转
        byteBuffer.flip();
        // byteBuffer的数据写入到channel
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
