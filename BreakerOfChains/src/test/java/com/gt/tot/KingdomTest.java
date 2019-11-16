package com.gt.tot;

import com.gt.tot.ballot.Ballot;
import com.gt.tot.kingdom.Air;
import com.gt.tot.kingdom.Kingdom;
import com.gt.tot.kingdom.Land;
import com.gt.tot.kingdom.Space;
import com.gt.tot.message.SecretsRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class KingdomTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private Air air = Air.kingdom();
    private Space space = Space.kingdom();
    private Land land = Land.kingdom();
    private Ballot ballot = Ballot.holyBallot();
    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();

    @Before
    public void reset() {
        ballot.reset();
        air.enterCompetition();
    }

    @Test
    public void kingdomShouldBeAbleToEnterCompetition() throws Exception {
        assertTrue(air.isCompeting());
    }

    @Test
    public void kingdomCannotSendMessageUnlessItEntersCompetition() throws Exception {
        air.resetState();
        air.accessSecretRepoAt(SecretsRepository.from("./src/test/resources/secrets.txt"));

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Can not send message without entering into competition");
        air.sendMessagesToKingdoms(southeros.kingdoms());
    }

    @Test
    public void shouldSendMessagesToAllOtherKingdoms() throws Exception {
        air.accessSecretRepoAt(SecretsRepository.from("./src/test/resources/secrets.txt"));
        air.sendMessagesToKingdoms(southeros.kingdoms());

        assertEquals(5, ballot.messageCounts());
    }

    @Test
    public void allKingdomsShouldBeAbleToEnterCompetitionAndSendMessagesToOther() throws Exception {
        for (Kingdom kingdom : southeros.kingdoms()) {
            kingdom.enterCompetition();
            kingdom.accessSecretRepoAt(SecretsRepository.from("./src/test/resources/secrets.txt"));
            kingdom.sendMessagesToKingdoms(southeros.kingdoms());
        }

        assertEquals(30, ballot.messageCounts());
    }

    @Test
    public void kingdomShouldTellNoneWhenThereAreNoAllies() throws Exception {
        assertEquals("None", air.alliesNames());
    }

    @Test
    public void kingdomShouldTellAlliesCount() throws Exception {
        assertEquals(0, air.alliesCount());
    }

    @Test
    public void kingdomShouldNotGiveAllegianceIfItIsCompetingEvenForRightSecretMessage() throws Exception {
        space.enterCompetition();

        space.receiveAllegianceMessageFrom(air, "Go or run it! voilla");
        assertEquals(0, air.alliesCount());
    }

    @Test
    public void kingdomShouldNotGiveAllegianceTwice() throws Exception {
        space.resetState();

        space.receiveAllegianceMessageFrom(air, "Go on running it! Ella");
        assertFalse(space.canGiveAlligiance());
        assertEquals(1, air.alliesCount());

        land.enterCompetition();
        space.receiveAllegianceMessageFrom(land, "oooggglllaaaiiir");
        assertEquals(0, land.alliesCount());
    }
}
