package com.gt.cricket.teams;

import com.gt.cricket.player.Player;
import com.gt.cricket.players.OutPlayer;
import com.gt.cricket.team.Team;

import java.util.Arrays;
import java.util.List;

public class TeamOut extends Team {

    private static final String name = "TeamOut";

    public TeamOut() {
        super(name, getPlayersList());
        this.nextIn = 2;
    }

    private static List<Player> getPlayersList() {
        Player first = new OutPlayer("First", name);
        Player second = new OutPlayer("Second", name);
        return Arrays.asList(first, second);
    }
}
