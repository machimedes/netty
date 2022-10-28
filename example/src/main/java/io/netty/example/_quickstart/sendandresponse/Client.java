package io.netty.example._quickstart.sendandresponse;

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

public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8089;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ByteBuf buffer = Unpooled.buffer(4);
        buffer.writeInt(65 + (65 << 8) + (65 << 16) + (65 << 24));

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

            f.channel().writeAndFlush(buffer);
            f.channel().writeAndFlush(buffer);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
