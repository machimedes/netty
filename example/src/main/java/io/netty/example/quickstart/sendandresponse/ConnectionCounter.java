package io.netty.example.quickstart.sendandresponse;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

@ChannelHandler.Sharable
public class ConnectionCounter extends ChannelInboundHandlerAdapter {
    private final AtomicInteger connections = new AtomicInteger();
    private static ConnectionCounter connectionCounter = new ConnectionCounter();

    private ConnectionCounter() {
    }

    public static ConnectionCounter getInstance() {
        return connectionCounter;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        connections.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        connections.decrementAndGet();
    }
}
