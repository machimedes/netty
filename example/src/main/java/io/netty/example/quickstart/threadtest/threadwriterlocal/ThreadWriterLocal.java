package io.netty.example.quickstart.threadtest.threadwriterlocal;

import io.netty.util.concurrent.FastThreadLocal;

public class ThreadWriterLocal extends FastThreadLocal<Appender> {
    @Override
    protected synchronized Appender initialValue() {

        return null;
    }

    @Override
    protected void onRemoval(Appender threadCache) {


    }


}
