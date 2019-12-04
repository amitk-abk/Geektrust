package com.gt.cricket.team;

import com.gt.cricket.player.Player;

import java.util.Arrays;
import java.util.List;

public class Team {

    public String name;
    public int nextIn = 2;
    public List<Player> players;
    private boolean battingSecond;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void teamIsBattingsecond(boolean battingSecond) {
        this.battingSecond = battingSecond;
    }

    public boolean isTeamBattingSecond() {
        return battingSecond;
    }

    public List<Player> startingBatsmen() {
        return Arrays.asList(this.players.get(0), this.players.get(1));
    }

    public Player nextBatsman() {
        if (this.nextIn < this.players.size())
            return this.players.get(this.nextIn++);
        else
            return null;
    }
}
