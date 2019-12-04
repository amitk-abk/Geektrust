package com.gt.cricket.teams;

import com.gt.cricket.players.OutPlayer;
import com.gt.cricket.players.SixerScorer;
import com.gt.cricket.player.Player;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamSixes extends Team {

    private static final String name = "TeamSixes";

    public TeamSixes() {
        super(name, getPlayersList());
        this.nextIn = 2;
    }

    private static List<Player> getPlayersList() {
        Player first = new SixerScorer("First", name);
        Player second = new SixerScorer("Second", name);
        return Arrays.asList(first, second);
    }
}