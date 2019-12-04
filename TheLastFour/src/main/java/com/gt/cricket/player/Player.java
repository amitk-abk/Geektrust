package com.gt.cricket.player;

import java.util.List;
import java.util.Random;

public class Player {

    private String name;
    private String team;
    private List<Integer> scoringProbabilities;
    private Boolean onStrike;

    public Player(String name, String team, List<Integer> scoringProbabilities) {
        this.name = name;
        this.team = team;
        this.scoringProbabilities = scoringProbabilities;
        this.onStrike = true;
    }

    public String getName() {
        return name;
    }

    List<Integer> getScoringProbabilities() {
        return scoringProbabilities;
    }

    public String playsDelivery() {
        return Scoring
                .offDelivery()
                .byPlayer(this, shotScore());
    }

    private int shotScore() {
        return new Random().nextInt(101);
    }

    public boolean isOnStrike() {
        return this.onStrike;
    }

    public void rotateStrike() {
        this.onStrike = !this.onStrike;
    }

    public void setOffStrike() {
        this.onStrike = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return team != null ? team.equals(player.team) : player.team == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
}
