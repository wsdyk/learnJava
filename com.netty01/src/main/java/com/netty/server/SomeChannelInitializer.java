package com.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

//管道初始化器
public class SomeChannelInitializer extends ChannelInitializer<SocketChannel> {

    //当Channel创建完毕后会触发改方法的执行，用于初始化Channel
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //从Channel中获取pipline
        ChannelPipeline pipeline = ch.pipeline();
        //将HttpServerCodec放到pipline之后

        pipeline.addLast(new HttpServerCodec());//HttpServerCodec 处理Channel中的ByteBuffer 编解码
        pipeline.addLast(new SomeServerHandler());
    }
}
