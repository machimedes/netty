package io.netty.example._quickstart.simplejsonrpc;

import io.netty.channel.DefaultFileRegion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Writer {
    RandomAccessFile file;
    long cnt = 0;

    Writer(String name) {
        new File(name);
        try {
            file = new RandomAccessFile(name, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                file.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void write(String message) {
        try {
            cnt++;
            file.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                file.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    void close() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
