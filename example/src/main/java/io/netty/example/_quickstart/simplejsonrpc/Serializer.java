package io.netty.example._quickstart.simplejsonrpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Serializer {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ByteBuf serialize(T... ts)  {
        List<T> list = new ArrayList<T>(Arrays.asList(ts));
        String json = null;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ByteBuf buffer = Unpooled.buffer(128);
        buffer.writeInt(json.length());
        buffer.writeBytes(json.getBytes(StandardCharsets.UTF_8));
        return buffer;
    }
}
