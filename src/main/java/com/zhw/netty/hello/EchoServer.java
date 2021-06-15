package com.zhw.netty.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final  EchoServerHandler serverHandler = new EchoServerHandler();
        // 1.创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 2.创建 ServerBootstrap
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(group)
            .channel(NioServerSocketChannel.class) // 3.指定所使用的NIO传输Channel
            .localAddress(new InetSocketAddress(port)) // 4.使用指定端口设置套接字地址
            .childHandler(new ChannelInitializer<SocketChannel>() { // 5. 添加一个EchoServerHandler到子ChannelI的ChannelPipeline
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(serverHandler);
                }
            });
            ChannelFuture f = b.bind().sync(); // 6. 异步的绑定
            f.channel().closeFuture().sync();// 7. 获取Channel的CloseFuture，并且阻塞当前线程知道他完成
        } finally {
            group.shutdownGracefully().sync(); // 8. 关闭EventLoopGroup释放所有资源
        }
    }
}
