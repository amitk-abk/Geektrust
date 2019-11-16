package com.gt.tot.ballot;

import com.gt.tot.UniverseOfSoutheros;
import com.gt.tot.kingdom.HighPriest;
import com.gt.tot.kingdom.Kingdom;
import com.gt.tot.message.Message;
import com.gt.tot.message.SecretsRepository;

import java.util.List;

public class Balloting {

    private UniverseOfSoutheros southeros = UniverseOfSoutheros.universeOfSoutheros();
    private Ballot holyBallot = Ballot.holyBallot();
    private HighPriest priest = HighPriest.of(southeros);
    private String secretsRepositoryLocation;

    private Balloting(String secretsRepositoryLocation) {
        this.secretsRepositoryLocation = secretsRepositoryLocation;
    }

    public static Balloting using(String secretsRepositoryLocation) {
        return new Balloting(secretsRepositoryLocation);
    }

    public ResultAnnouncer ballotingSoutherosRuler(List<Kingdom> participatingKingdoms) {
        kingdomsEnterIntoCompitition(participatingKingdoms);
        kingdomsSendMessageSelectedFrom(participatingKingdoms);
        priestPollsHolyBallotToSelectChosenFew();
        clearBallotAfterSelection();
        return getResultAnnouncer(participatingKingdoms);
    }

    private void kingdomsEnterIntoCompitition(List<Kingdom> participatingKingdoms) {
        for (Kingdom kingdom: participatingKingdoms)
            kingdom.enterCompetition();
    }

    private void kingdomsSendMessageSelectedFrom(List<Kingdom> participatingKingdoms) {
        SecretsRepository secretsRepository = SecretsRepository.from(secretsRepositoryLocation);
        for (Kingdom kingdom: participatingKingdoms) {
            kingdom.accessSecretRepoAt(secretsRepository);
            kingdom.sendMessagesToKingdoms(southeros.kingdoms());
        }
    }

    private void priestPollsHolyBallotToSelectChosenFew() {
        List<Message> messages = priest.drawChosenFewFrom(holyBallot);
        priest.handoverMessages(messages);
    }

    private void clearBallotAfterSelection() {
        holyBallot.reset();
    }

    private ResultAnnouncer getResultAnnouncer(List<Kingdom> participatingKingdoms) {
        return ResultAnnouncer.forKingdoms(participatingKingdoms);
    }
}
