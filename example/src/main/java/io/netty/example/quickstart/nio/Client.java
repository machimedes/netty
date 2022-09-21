package io.netty.example.quickstart.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 5454));
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
        client.write(buffer);
        buffer.clear();
        Thread.sleep(5000);

        ByteBuffer buffer1 = ByteBuffer.wrap("hello world1".getBytes());
        client.write(buffer1);

        client.close();
    }
}
