package io.netty.example._quickstart._nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static io.netty.example._quickstart._nio.DecodeState.R_CONTENT;
import static io.netty.example._quickstart._nio.DecodeState.R_LENGTH;

public class SimpleServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 5454));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            Thread.sleep(3000);
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ, new WrappedSocketChannel(client));
                }

                if (key.isReadable()) {
                    WrappedSocketChannel client = (WrappedSocketChannel) key.attachment();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    ((SocketChannel) key.channel()).read(buffer);
                    if(buffer.position()>0){
                        lengthFieldDecode(buffer, client);
                    }
                }
                iter.remove();
            }
        }
    }

    public static void lengthFieldDecode(ByteBuffer bb, WrappedSocketChannel client) {
        DecodeState state = client.state;

        ByteBuffer in = client.in;
        if (in == null) {
            client.in = bb;
        } else {
            bb.flip();
            in.put(bb);
        }
        System.out.println(client.in);


        boolean stop = false;
        while (!stop) {
            switch (state) {
                case R_LENGTH: {
                    assert in != null;
                    in.flip();
                    if (in.remaining() >= 4) {
                        client.length = in.getInt();
                        System.out.println(client.length);
                        state = R_CONTENT;
                    } else {
                        stop = true;
                        break;
                    }
                }

                case R_CONTENT: {
                    assert in != null;
                    if (in.remaining() >= client.length) {
                        byte[] array = new byte[client.length];
                        client.in.get(array);
                        System.out.println(new String(array));
                        state = R_LENGTH;
                    } else {
                        stop = true;
                        break;
                    }
                }
            }
        }
    }
}


