package com.zhw.netty.helloservcer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HelloServer {
    public static void main(String[] args) {
        // 创建 EventLoopGroup 线程组用于接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // 创建worker 线程组，用于进行SocketChannel 的数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建引导
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置NioServerSocketChannel的处理器
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    // 设置连入服务端的client SocketChannel 的处理器
                    .childHandler(new ServerInitializer());

            ChannelFuture future = bootstrap.bind(8888);

            // 调用 #closeFuture() 监听服务器关闭，后调用 #sync() 方法，阻塞等待成功。
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
