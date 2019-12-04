package com.gt.cricket.teams;

import com.gt.cricket.player.Player;
import com.gt.cricket.players.OutPlayer;
import com.gt.cricket.players.SixerScorer;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamSkewed extends Team {

    private static final String name = "TeamSkewed";

    public TeamSkewed() {
        super(name, getPlayersList());
        this.players = getPlayersList();
    }

    private static List<Player> getPlayersList() {
        Player first = new OutPlayer("First", name);
        Player second = new SixerScorer("Second", name);
        Player third = new OutPlayer("Third", name);
        Player fourth = new OutPlayer("Fourth", name);
        return Arrays.asList(first, second, third, fourth);
    }
}
