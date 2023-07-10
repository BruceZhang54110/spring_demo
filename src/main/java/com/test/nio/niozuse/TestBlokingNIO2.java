package com.test.nio.niozuse;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlokingNIO2 {

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel inChannel = FileChannel.open(Paths.get("E:\\JavaDev\\socketTest_22.txt"), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        socketChannel.shutdownOutput();
        // 接收服务端的反馈
        int len = 0;
        while((len = socketChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }


        inChannel.close();
        socketChannel.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("E:\\JavaDev\\socketTest_33.txt"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        serverSocketChannel.bind(new InetSocketAddress(9898));
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while(socketChannel.read(buf) != -1) {
            buf.flip();
            fileChannel.write(buf);
            buf.clear();
        }
        // 反馈给客户端
        buf.put("服务端接收成功".getBytes());
        buf.flip();
        socketChannel.write(buf);
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();

    }
}
