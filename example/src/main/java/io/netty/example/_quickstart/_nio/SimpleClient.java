package io.netty.example._quickstart._nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SimpleClient {
    static int INTEGER_SIZE = 4;

    public static void main(String[] args) throws IOException, InterruptedException {
        String message = "hello";
        ByteBuffer buffer = lengthFieldEncode(message);


        SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 5454));
        client.write(buffer);

        Thread.sleep(5000);

        client.close();
    }

    public static ByteBuffer lengthFieldEncode(String message) {
        final byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocateDirect(INTEGER_SIZE + bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.rewind();
        printByteBufferAsCharSequence(buffer);
        return buffer;
    }

    public static void printByteBufferAsCharSequence(ByteBuffer bb) {
        while (bb.hasRemaining()) {
            System.out.print((char) bb.get() + " ");
        }
        System.out.println();
        bb.rewind();
        System.out.println(bb);
    }
}
