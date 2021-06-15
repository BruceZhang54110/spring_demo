package com.at.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();
            // 得到ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress("127.0.0.1", PORT));
            // 设置非阻塞
            listenChannel.configureBlocking(false);
            // 注册
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {

        }
    }

    /**
     * 监听方法
     */
    public void listen() throws IOException {
        // 循环
        while(true) {
           int count = selector.select(2000);
           if (count > 0) {
                // 有事件
               // 遍历selectionKey
               Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
               while (keysIterator.hasNext()) {
                   SelectionKey selectionKey = keysIterator.next();
                   if (selectionKey.isAcceptable()) {
                       SocketChannel sc = listenChannel.accept();
                       sc.configureBlocking(false);
                       sc.register(selector, SelectionKey.OP_READ);
                       // 提示
                       System.out.println(sc.getRemoteAddress() + " 上线了");
                   }
                   if (selectionKey.isReadable()) {// 通道发生read事件，即通道是可读的状态
                        // 读取事件（有数据需要读取）
                        readData(selectionKey);

                   }
                   // 删除当前key,防止重复处理
                   keysIterator.remove();
               }
           } else {
               System.out.println("server is wating...");
           }
        }

    }

    private void readData(SelectionKey key) {
        // 定义一个SocketChannel
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            // 创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            // 根据count的值做处理
            if (count > 0) {
                // 把缓冲区的数据转成字符串
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端：" + msg);
                // 向其他客户端转发消息
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + ", 离线");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他的客户
     */
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息...start");
        // 遍历所有注册到selection中的SocketChannel，排除自己
        for (SelectionKey key : selector.keys()) {
            // 通过key取出对应的SocketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel)targetChannel;
                // 将msg存储到Buffer
                ByteBuffer msgBuffer = ByteBuffer.wrap(msg.getBytes());
                // 写入到通道中
                dest.write(msgBuffer);
            }
        }
    }

    public static void main(String[] args) {

    }
}
