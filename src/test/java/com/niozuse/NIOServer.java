package com.niozuse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServierSocketChannel -> ServerSocket

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到Selector对象
        Selector selector = Selector.open();

        // 绑定一个端口6666，在服务器短监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把ServerSocketChannel 注册到 Selector 关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while(true) {
            // 这里等待1s
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1s, 无连接");
                continue;
                // 没有事件
            }
            // 如果返回的不是0
            // 返回关注事件的集合
            // 通过 selectionKeys 获取相关通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                // 根据 key对应的通道发生的事件做相应的处理
                if (selectionKey.isAcceptable()) { // 有新的客户端连接
                    // 如果是连接事件
                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel设置非阻塞
                    socketChannel.configureBlocking(false);
                    // 注册到selector， 关注读事件，并关联一个Buffer
                    System.out.println("客户端连接成功，生成了一个SocketChannel, hashCode: " + socketChannel.hashCode());
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) { // 发生读取事件
                    // 通过key 反向获取到channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("from 客户端: " + new String(buffer.array(), StandardCharsets.UTF_8));
                }
                // 手动从集合中删除当前key，防止重复操作
                selectionKeyIterator.remove();
            }
        }

    }
}
