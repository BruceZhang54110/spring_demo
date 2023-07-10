package com.test.nio.niozuse.channeltest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * transferFrom 文件拷贝
 */
public class NioFileChannel04 {
    public static void main(String[] args) throws IOException {
        // 创流
        File file = new File("1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel sourceChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("copy1.txt");
        FileChannel targetChannel = fileOutputStream.getChannel();

        // 使用transform完成拷贝
        targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        targetChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
