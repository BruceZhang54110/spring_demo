package com.zhw.netty.helloservcer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 初始化SocketChannel 的处理器
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();

    private static final StringEncoder ENCODER = new StringEncoder();

    private static final ClientHandler CLIENT_HANDLER = new ClientHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加帧限定符来防止粘包现象
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 编码解码，应该和客户端一样
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);
        // 业务逻辑实现类
        pipeline.addLast(CLIENT_HANDLER);
    }
}
