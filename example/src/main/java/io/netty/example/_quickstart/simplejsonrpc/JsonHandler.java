package io.netty.example._quickstart.simplejsonrpc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class JsonHandler extends SimpleChannelInboundHandler<String> {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        long timestamp = System.currentTimeMillis();
        List<User> users = objectMapper.readValue(msg, new TypeReference<List<User>>() {
        });
        for (User user : users) {
            Wrapper wrapper = Wrapper.userRecycler.get();
            wrapper.setTimestamp(timestamp);
            wrapper.setMessage(user);
            System.out.println(objectMapper.writeValueAsString(wrapper));
            wrapper.recycle();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}