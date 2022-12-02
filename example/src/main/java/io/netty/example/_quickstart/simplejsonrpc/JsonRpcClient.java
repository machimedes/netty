package io.netty.example._quickstart.simplejsonrpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class JsonRpcClient {
    static String host = "localhost";
    static int port = 8089;
    static EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) {

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new LengthFieldBasedFrameDecoder(Short.MAX_VALUE, 0, 4)
                            , new StringDecoder(CharsetUtil.UTF_8)
                            , new PrintHandler());
                }
            });

            final ChannelFuture f = b.connect(host, port).sync();
            System.out.println("连接到服务端");

            while (true) {
                System.out.println("发送消息");
                ByteBuf byteBuf = Serializer.serialize(
                        new User(0, "1", "hello world"),
                        new User(1, "2", "google"));

                f.channel().writeAndFlush(byteBuf);
                Thread.sleep(5000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
