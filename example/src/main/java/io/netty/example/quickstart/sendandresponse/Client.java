package io.netty.example.quickstart.sendandresponse;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8089;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            final ChannelFuture f = b.connect(host, port).sync();
            System.out.println("开始发消息");
            ByteBuf buffer1 = Unpooled.buffer(4);
            buffer1.writeInt(65 + (65 << 8) + (65 << 16) + (65 << 24));

            ChannelFuture channelFuture = f.channel().writeAndFlush(buffer1);
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> fp) throws Exception {
                    System.out.println("hello world");
                }
            }).sync();
            System.out.println("发送完毕");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        f.channel().close().sync();
                        System.out.println("another thread");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            f.channel().closeFuture().sync();
            System.out.println("main thread");

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
