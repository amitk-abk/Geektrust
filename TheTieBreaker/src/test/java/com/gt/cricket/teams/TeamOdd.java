package com.gt.cricket.teams;

import com.gt.cricket.players.OddRunScorer;
import com.gt.cricket.player.Player;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamOdd extends Team {

    private static final String name = "TeamOdd";

    public TeamOdd() {
        super(name, getPlayersList());
    }

    private static List<Player> getPlayersList() {
        Player first = new OddRunScorer("First", name);
        Player second = new OddRunScorer("Second", name);
        return Arrays.asList(first, second);
    }
}
