package com.gt.cricket.game;

import com.gt.cricket.player.Player;
import com.gt.cricket.player.Scoring;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ScoringTest {

    Scoring scoring = Scoring.offDelivery();

    @Test
    public void shouldGetScorePerProbabilityDistributionForDefaultCase() throws Exception {
        Player player1 = new Player("any player", "team", Arrays.asList(10,10,10,10,10,10,10,30));
        assertEqualsScore(getScoreFor(player1, 1), "0");
        assertEqualsScore(getScoreFor(player1, 11), "1");
        assertEqualsScore(getScoreFor(player1, 21), "2");
        assertEqualsScore(getScoreFor(player1, 31), "3");
        assertEqualsScore(getScoreFor(player1, 41), "4");
        assertEqualsScore(getScoreFor(player1, 51), "5");
        assertEqualsScore(getScoreFor(player1, 61), "6");
        assertEqualsScore(getScoreFor(player1, 71), "out");
        assertEqualsScore(getScoreFor(player1, 81), "out");
        assertEqualsScore(getScoreFor(player1, 91), "out");
    }

    private String getScoreFor(Player player, int i) {
        return scoring.byPlayer(player, i);
    }

    private void assertEqualsScore(String actual, String expected) {
        assertEquals(expected, actual);
    }
}
