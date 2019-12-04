package com.gt.cricket.teams;

import com.gt.cricket.player.Player;
import com.gt.cricket.players.OneRunScorer;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamOnes extends Team {

    private static final String name = "TeamOnes";

    public TeamOnes() {
        super(name, getPlayersList());
        this.nextIn = 2;
    }

    private static List<Player> getPlayersList() {
        Player first = new OneRunScorer("First", name);
        Player second = new OneRunScorer("Second", name);
        return Arrays.asList(first, second);
    }
}