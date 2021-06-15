package com.test.nio.niozuse;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通道的三个核心：
 * 1、 通道（Chinnel）:负责连接
 *      java.nio.Channel
 *          |-
 * 2、 缓冲区（Buffer）:负责数据的存取
 * 3、 选择器（Selector）: SelecttableChannel 的多路复用器。用于监控 SelectableChannel 的IO状况
 */
public class TestBlokingNIO {

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        // 1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 3. 读取本地文件，并发送到服务端去
        FileChannel inChannel = FileChannel.open(Paths.get("E:\\JavaDev\\Test_22.txt"), StandardOpenOption.READ);
        while(inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        // 4.关闭通道
        inChannel.close();
        socketChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    public void server() throws IOException {
        // 1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("E:\\JavaDev\\socketTest_22.txt"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.READ);
        // 2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));
        // 3.获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 4. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 5. 接受客户端的数据，并保存到本地
        while(socketChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        // 6.关闭通道
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
