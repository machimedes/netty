package io.netty.example._quickstart.emoji;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PrintEmoji {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "\uD83D\uDE09";
        System.out.println(Arrays.toString(s.getBytes(StandardCharsets.UTF_8)));
        byte[] a = {(byte) -16, (byte) -97, (byte) -104, (byte) -120};
        s = new String(a, "UTF-8");
        System.out.println(s);
    }
}
