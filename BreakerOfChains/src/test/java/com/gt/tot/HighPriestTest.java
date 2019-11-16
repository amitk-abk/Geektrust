package com.gt.tot;

import com.gt.tot.ballot.Ballot;
import com.gt.tot.kingdom.HighPriest;
import com.gt.tot.kingdom.Kingdom;
import com.gt.tot.message.Message;
import com.gt.tot.message.SecretsRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class HighPriestTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private Ballot holyBallot = Ballot.holyBallot();
    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();
    private SecretsRepository secretsRepository = SecretsRepository.from("./src/test/resources/secrets.txt");

    @Test
    public void shouldBeAbleToSelectMessagesFromBallotAfterKingdomsSendMessages() throws Exception {
        HighPriest priest = HighPriest.of(southeros);

        for (Kingdom kingdom : southeros.kingdoms()) {
            kingdom.enterCompetition();
            kingdom.accessSecretRepoAt(secretsRepository);
            kingdom.sendMessagesToKingdoms(southeros.kingdoms());
        }

        List<Message> chosenFew = priest.drawChosenFewFrom(holyBallot);
        assertFalse(chosenFew.isEmpty());
    }

    @Test
    public void canNotPollFromEmptyBallot() throws Exception {
        holyBallot.reset();
        HighPriest priest = HighPriest.of(southeros);

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Holy Ballot is empty!!");
        priest.drawChosenFewFrom(holyBallot);
    }
}
