package com.gt.cricket.teams;

import com.gt.cricket.players.EvenRunScorer;
import com.gt.cricket.players.OddRunScorer;
import com.gt.cricket.player.Player;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamOddEven extends Team {

    private static final String name = "TeamOddEven";

    public TeamOddEven() {
        super(name, getPlayersList());
        this.nextIn = 2;
    }

    private static List<Player> getPlayersList() {
        Player first = new OddRunScorer("First", name);
        Player second = new EvenRunScorer("Second", name);
        return Arrays.asList(first, second);
    }
}
