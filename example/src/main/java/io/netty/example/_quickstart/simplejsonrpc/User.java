package io.netty.example._quickstart.simplejsonrpc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("greeting_word")
    String greetingWord;

    public User() {
    }

    public User(long id, String name, String greetingWord) {
        this.id = id;
        this.name = name;
        this.greetingWord = greetingWord;
    }
}
