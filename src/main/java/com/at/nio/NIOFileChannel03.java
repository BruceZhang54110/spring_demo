package com.at.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 拷贝文件内容到另一个文件
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("1.txt");
        final FileChannel fileChannel = fis.getChannel();

        final FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        final FileChannel outputStreamChannel = fileOutputStream.getChannel();
        // 创建缓冲区
        final ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while(true) {
            byteBuffer.clear();
            int read = fileChannel.read(byteBuffer);
            if (read < 0) {
                break;
            }
            // 将buffer中的文件写入到02.txt
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        fis.close();
        fileOutputStream.close();

    }
}
