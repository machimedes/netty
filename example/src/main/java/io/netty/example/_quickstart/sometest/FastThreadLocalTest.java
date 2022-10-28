package io.netty.example._quickstart.sometest;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import static java.lang.Math.min;

public class FastThreadLocalTest {
    public static void main(String[] args) {
        final FastThreadLocal<Integer> ft = new FastThreadLocal<>();
        ft.set(1);

        new FastThreadLocalThread(new Runnable() {
            @Override
            public void run() {
                ft.set(2);
            }
        }).start();

    }
}
