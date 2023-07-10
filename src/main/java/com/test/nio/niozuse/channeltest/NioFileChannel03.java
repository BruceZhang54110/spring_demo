package com.test.nio.niozuse.channeltest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * 从文件中读取内容到另一个文件
 */
public class NioFileChannel03 {
    public static void main(String[] args) throws IOException {
        // 创流
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            // 清空
            byteBuffer.clear();
            int read = fileInputStreamChannel.read(byteBuffer);
            if (read == -1) {
                // 表示读取结束
                break;
            }
            // 将buffer中数据写入到fileOutputStreamChannel
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        }
        byteBuffer.flip();

        // 将字节转字符串
        byte[] array = byteBuffer.array();
        System.out.println(new String(array));

        // 通过输出流获取对应的文件Channel
        fileInputStream.close();
        fileOutputStream.close();
    }
}
