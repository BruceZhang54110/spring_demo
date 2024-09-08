package com.niozuse;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * NIO非阻塞式
 */
public class TestNonBlockingNIO {

    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9892));
        // 切换成非阻塞模式
        sChannel.configureBlocking(false);
        // 分配指定大小的缓冲器
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 发送数据给服务端
        buf.put(LocalDateTime.now().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();
        // 关闭通道
        sChannel.close();
    }
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 切换成非阻塞模式
        ssChannel.configureBlocking(false);
        // 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上register(selector, ops 选择键)

        //ssChannel.register(selector, )
    }
}
