package io.netty.example.quickstart.buffertest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.NettyRuntime;

import java.io.BufferedReader;
import java.nio.ByteBuffer;

import static java.lang.Math.min;

public class ByteBufTest {
    public static void main(String[] args) {
        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);

        int defaultMinNumArena = min(NettyRuntime.availableProcessors() * 2, 2);
        ByteBufAllocator pooledByteBufAllocator;
        int pageSize = 1 << 13;
        int capacity = pageSize << 1;
        int maxOrder = 2;
        pooledByteBufAllocator = new PooledByteBufAllocator(true,
                defaultMinNumArena,
                defaultMinNumArena,
                pageSize,
                maxOrder);

        ByteBuf byteBuf1 =pooledByteBufAllocator.directBuffer(capacity);
        byteBuf1.release();
        ByteBuf byteBuf2 =pooledByteBufAllocator.directBuffer(capacity);
        byteBuf2.release();

    }
}
