package com.gt.tot.kingdom;

import com.gt.tot.UniverseOfSoutheros;
import com.gt.tot.ballot.Ballot;
import com.gt.tot.message.Message;

import java.util.ArrayList;
import java.util.List;

public class HighPriest {
    private final UniverseOfSoutheros southeros;

    private HighPriest(UniverseOfSoutheros universeOfSoutheros) {
        southeros = universeOfSoutheros;
    }

    public static HighPriest of(UniverseOfSoutheros universeOfSoutheros) {
        return new HighPriest(universeOfSoutheros);
    }

    public List<Message> drawChosenFewFrom(Ballot holyBallot) {
        List<Message> chosenFew = new ArrayList<>();
        if (holyBallot.isNotEmpty()) {
            int limit = holyBallot.messageCounts() < 6 ? holyBallot.messageCounts() : 6;
            for (int i = 0; i < limit; i++) {
                chosenFew.add(holyBallot.poll());
            }
        } else
            throw new RuntimeException("Holy Ballot is empty!!");
        return chosenFew;
    }


    public void handoverMessages(List<Message> chosenFew) {
        for (Message message: chosenFew) {
            Kingdom sender = message.getSender();
            Kingdom receiver = message.getReceiver();
            String secret = message.getSecret();

            receiver.receiveAllegianceMessageFrom(sender, secret);
        }
    }
}
