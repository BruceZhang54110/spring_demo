package com.at.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 */
public class NioFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("d:\\teamviewer15wmpjb_160852.rar");
        FileOutputStream fos = new FileOutputStream("d:\\teamviewer15.rar");
        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fos.getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        destChannel.close();
        fileInputStream.close();
        fos.close();
    }
}
