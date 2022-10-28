package io.netty.example._quickstart.threadtest.threadwriterlocal;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class RandomAccessFileAppender implements Appender {

    RandomAccessFile raf;

    public void open(String path) throws FileNotFoundException {

        raf = new RandomAccessFile(path, "rw");
    }


}
