package com.gt.cricket;

import com.gt.cricket.init.Initializer;
import com.gt.cricket.team.Team;

public class Main {

    public static void main(String[] args) {
        Initializer initializer = new Initializer("./src/main/resources/players.txt");
        Team lengaburu = new Team("Lengaburu", initializer.playersOf("Lengaburu"));
        Team enchai = new Team("Enchai", initializer.playersOf("Enchai"));
        enchai.teamIsBattingsecond(true);

        new TieBreaker(lengaburu, enchai).start();
    }

}
