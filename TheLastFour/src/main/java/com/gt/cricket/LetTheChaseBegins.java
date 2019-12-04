package com.gt.cricket;

import com.gt.cricket.init.Initializer;
import com.gt.cricket.team.Team;

public class LetTheChaseBegins {

    public static void main(String[] args) {
        Initializer initializer = new Initializer("./src/main/resources/players.txt");
        Team lengaburu = new Team("Lengaburu", initializer.playersOf("Lengaburu"));
        lengaburu.teamIsBattingsecond(true);

        new TargetChase.By(lengaburu)
                .targetOf(40)
                .inOvers(4)
                .begin();
    }
}
