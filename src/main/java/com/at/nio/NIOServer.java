package com.at.nio;

import io.netty.buffer.ByteBuf;
import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 1.创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2. 得到一个Selector
        Selector selector = Selector.open();
        // 3.绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 4. 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 5.把我们的ServerSocketChannel 注册到 selector ，关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6. 循环等待客户端连接
        while (true) {
            // 等待1s
            if (selector.select(1000) == 0) { // 没有事件发生
                System.out.println("服务器等待了1s, 无连接");
                continue;
            }
            // 如果返回不是0, 获取到selectionKey 集合
            // 如果返回大于0 ，表示获取到了关注的事件
            // 通过selectionKeys 反向获取到Channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                // 根据发生的事件做相应的处理
                if (selectionKey.isAcceptable()) { // 如果是OP_ACCEPT ，有客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功, 生成 socketChannel: " + socketChannel.hashCode());

                    // 將 socketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 将socketChannel注册到selector,关注事件是 OP_READ ，同时给socketChannel关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    System.out.println("客户端发送过来了数据");
                    // 读取事件
                    // 通过key反向获取channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取到该Channel 关联的Buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("客户端发送：" + new String(byteBuffer.array()));
                }
                // 手动移除当前的 selectionKey, 防止重复操作
                keyIterator.remove();
            }
        }

    }
}
