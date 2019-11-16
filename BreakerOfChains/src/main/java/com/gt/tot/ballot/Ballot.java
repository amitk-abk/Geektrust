package com.gt.tot.ballot;

import com.gt.tot.message.Message;

import java.util.*;

public class Ballot {

    private static final Ballot HOLY_BALLOT = new Ballot();
    private final Set<Message> messages;

    private Ballot() {
        messages = new HashSet<>();
    }

    public static Ballot holyBallot() {
        return HOLY_BALLOT;
    }

    public int messageCounts() {
        return messages.size();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void reset() {
        messages.clear();
    }

    public Message poll() {
        Random random = new Random();
        List<Message> msgs = new ArrayList<>(messages);
        Collections.shuffle(msgs);
        return msgs.get(random.nextInt(msgs.size()));
    }

    public boolean isNotEmpty() {
        return !messages.isEmpty();
    }
}
