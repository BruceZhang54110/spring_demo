package com.test.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在Java 中负责数据的存取。
 * 缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型的不同（boolean除外）提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBufer
 * 上述缓冲区的管理方式几乎一致，通过allocate() 获取缓冲区
 *二、缓冲区存入数据的两个核心方法：
 * put()
 * get()
 */
public class TestBuffer {
    public static void main(String[] args) {
        String abc = "abcde";

        // 1.分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("------------put()--------------------------");

        // 2.利用put()方法存入数据
        byteBuffer.put(abc.getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("------------flip()--------------------------");

        // 3.切換成读取数据的模式，读数据
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("------------get()--------------------------");
        // 4. 读取缓冲区的数据
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println(new String(dst));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("-------------------rewind()-------------------------------");
        byteBuffer.rewind();// 可重复读数据
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());


        System.out.println("-------------------rewind()后读取-------------------------------");
        System.out.println((char)byteBuffer.get());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("--------------------mark()---------------");
        byteBuffer.mark(); // 进行标记
        System.out.println("再次读取");
        System.out.println((char)byteBuffer.get());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        // reset()重置，将position恢复到mark
        System.out.println("------------reset()------------------");
        byteBuffer.reset();
        System.out.println("------------position()------------------");
        System.out.println(byteBuffer.position());
        System.out.println("------------get------------------");
        System.out.println((char)byteBuffer.get());

        // 清空缓冲区，但是数据依然存在
        System.out.println("---------------------clear()-----------------------------");
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

    }


}
