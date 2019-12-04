package com.gt.cricket.game;

import com.gt.cricket.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class BatsmanTest {

    private Player batsman;

    @Before
    public void init() {
        batsman = new Player("player name", "team", Arrays.asList(10, 10, 10, 10, 10, 10, 10, 30));
    }

    @Test
    public void shouldTakeStrikeWhenComesToBatting() throws Exception {
        assertTrue(batsman.isOnStrike());
    }

    @Test
    public void shouldPlayTheDeliveryForResult() throws Exception {
        String result = batsman.playsDelivery();

        List<String> possibleOutcomes = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "out");
        assertTrue(possibleOutcomes.contains(result));
    }
}
