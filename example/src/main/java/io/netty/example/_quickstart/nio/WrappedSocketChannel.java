package io.netty.example._quickstart.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WrappedSocketChannel {
    public WrappedSocketChannel(SocketChannel sc) {
        client = sc;
    }

    public SocketChannel client;
    public ByteBuffer in = ByteBuffer.allocate(1024);
    public DecodeState state = DecodeState.R_CONTENT;
    public int length = 0;
}
