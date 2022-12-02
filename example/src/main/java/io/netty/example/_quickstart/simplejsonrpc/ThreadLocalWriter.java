package io.netty.example._quickstart.simplejsonrpc;

import io.netty.util.concurrent.FastThreadLocal;

public class ThreadLocalWriter extends FastThreadLocal<Writer> {
    @Override
    protected Writer initialValue() {
        try {
            super.initialValue();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new Writer(Thread.currentThread().getName());
    }

    @Override
    protected void onRemoval(Writer value) throws Exception {
        super.onRemoval(value);
        value.close();
    }
}


