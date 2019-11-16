package com.gt.tot;

import com.gt.tot.ballot.Balloting;
import com.gt.tot.ballot.ResultAnnouncer;
import com.gt.tot.kingdom.Air;
import com.gt.tot.kingdom.Kingdom;
import com.gt.tot.kingdom.Land;
import com.gt.tot.kingdom.Space;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BallotingTest {

    private Air air = Air.kingdom();
    private Land land = Land.kingdom();
    private Space space = Space.kingdom();
    private List<Kingdom> participatingKingdoms = Arrays.asList(air, land, space);
    private Balloting balloting;

    @Test
    public void shouldCompeteForRulingUniverse() throws Exception {
        balloting = Balloting.using("./src/test/resources/secrets.txt");

        Map<String, String> result = announcedBallotingResult();
        String ballotingResult = result.get("result");

        List<String> possibleResults = Arrays.asList("tie", air.getName(), land.getName(), space.getName());
        assertTrueThat(possibleResults.contains(ballotingResult));

        if (ballotingResult.equals("Air"))
            assertTrueThat(result.get("Air").startsWith("Allies for Air: "));
        else if (ballotingResult.equals("Land"))
            assertTrueThat(result.get("Land").startsWith("Allies for Land: "));
        else if (ballotingResult.equals("Space"))
            assertTrueThat(result.get("Space").startsWith("Allies for Space: "));
    }

    @Test
    public void shouldDeclareTieResult() throws Exception {
        balloting = Balloting.using("./src/test/resources/tiesecrets.txt");

        Map<String, String> result = announcedBallotingResult();
        String ballotingResult = result.get("result");

        assertTrueThat("tie".equals(ballotingResult));
    }

    private Map<String, String> announcedBallotingResult() {
        ResultAnnouncer resultAnnouncer = balloting.ballotingSoutherosRuler(participatingKingdoms);
        return resultAnnouncer.announce();
    }

    private void assertTrueThat(boolean b) {
        assertTrue(b);
    }

}
