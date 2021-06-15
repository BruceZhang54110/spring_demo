package com.at.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Buffer的分散和聚集
 * Scttering: 将数据写入到Buffer时，可以采用Buffer数组依次写入(分散)
 * gathering: 从Buffer读取数据时，采用Buffer数组一次读取[聚集]
 *
 */
public class SctteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        // 使用ServerSocketChannel 和 SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        // 绑定端口到socket
        serverSocketChannel.socket().bind(inetSocketAddress);

        // 创建Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLen = 8; // 从客户端接受8个字节
        // 循环读取
        while(true) {
            long byteRead = 0l;
            while(byteRead < messageLen) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("byte read : " + byteRead);
                // 使用流打印
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position=" +
                        byteBuffer.position() + ", limit= " + byteBuffer.limit()).forEach(System.out::println);
            }
            // 将所有buffer全部反转
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            // 将数据读出显示回客户端
            long byteWrite = 0L;
            while(byteWrite < messageLen) {
                long l = socketChannel.write(byteBuffers);// 回送
                byteWrite += l;
            }

            // 将所有buffer 进行 clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead= " + byteRead + ", byteWrite= " + byteWrite + "messageLen= " +messageLen);

        }

    }
}
