package io.netty.example._quickstart.simplejsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.netty.util.Recycler;

public class Wrapper {
    long timestamp;
    Object message;

    public static final Recycler<Wrapper> userRecycler = new Recycler<Wrapper>() {
        @Override
        protected Wrapper newObject(Handle<Wrapper> handle) {
            return new Wrapper(handle);
        }
    };

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object getMessage() {
        return message;
    }

    @JsonIgnore
    Recycler.Handle<Wrapper> recyclerHandle;

    void recycle() {
        recyclerHandle.recycle(this);
    }


    public Wrapper(Recycler.Handle<Wrapper> handle) {
        this.recyclerHandle = handle;
    }
}
