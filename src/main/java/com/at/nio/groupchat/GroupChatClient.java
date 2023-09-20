package com.at.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GroupChatClient {
    private static final String HOST = "127.0.0.1"; // 服务器Ip
    private final int PORT = 6667; // 服务器端口

    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    // 构造器完成初始化工作
    public GroupChatClient() throws Exception{

        selector = Selector.open();
        // 连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName =socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + "is ok。");
    }

    //向服务器发送消息
    public void sendInfo(String info) throws IOException {
        info += userName + "说：" + info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

                                                                      
    //读取从服务器端回复的消息
    public void readInfo() {
        try {
            final int select = selector.select();
            if (select > 0) {  // 又可以用的通道
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        // 得到相关的通道
                        SocketChannel sc = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    iterator.remove();
                }
            } else {
                System.out.println(userName + "：客户端没有可用通道");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        // 启动客户端
        final GroupChatClient chatClient = new GroupChatClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    chatClient.readInfo();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            chatClient.sendInfo(scanner.nextLine());
        }
    }
}
