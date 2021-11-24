package com.netty.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class SomeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg:"+msg.getClass());
        System.out.println("IP:"+ctx.channel().remoteAddress());
        if (msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            System.out.println("请求方式："+request.method().name());
            System.out.println("请求UrI"+request.uri());

            ByteBuf body = Unpooled.copiedBuffer("hello netty world", CharsetUtil.UTF_8);
            new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK,body);



        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
