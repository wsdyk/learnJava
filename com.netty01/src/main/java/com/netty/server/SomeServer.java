package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SomeServer {

    public static void main(String[] args) {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        //启动serverchannel
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentGroup,childGroup)//指定EventLoopGroup
                .channel(NioServerSocketChannel.class)//指定NIO进行通信
                . childHandler(new SomeChannelInitializer());//指定ChildGroup中的eventloop所绑定的线程所要处理的处理器
        try {

            //指定监听端口号 bind异步 sync()使之变为同步
            ChannelFuture future = bootstrap.bind(8848).sync();
            System.out.println("服务器启动，监听端口号是8848");
            //关闭管道
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅关闭
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }


    }
}
