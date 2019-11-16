package com.gt.tot.message;

import com.gt.tot.kingdom.Kingdom;

import java.util.Objects;

public class Message {

    private Kingdom sender;
    private Kingdom receiver;
    private String secret;

    public Message(Kingdom sender, Kingdom receiver, String secret) {
        this.sender = sender;
        this.receiver = receiver;
        this.secret = secret;
    }

    public Kingdom getSender() {
        return sender;
    }

    public Kingdom getReceiver() {
        return receiver;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) &&
                Objects.equals(receiver, message.receiver) &&
                Objects.equals(secret, message.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, secret);
    }
}
