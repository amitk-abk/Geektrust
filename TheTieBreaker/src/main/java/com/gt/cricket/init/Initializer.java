package com.gt.cricket.init;

import com.gt.cricket.player.Player;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Initializer {

    private String initializationFile;

    public Initializer(String file) {
        initializationFile = file;
    }

    public List<Player> playersOf(String team) {
        return initialize(team);
    }

    private List<Player> initialize(String team) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(initializationFile)))) {
            String input = reader.readLine();

            while (!input.startsWith(team))
                input = reader.readLine();

            while (!input.equalsIgnoreCase("q") && input.length() > 0) {
                String details[] = input.split(Pattern.quote(","));
                if (details.length != 10)
                    throw new RuntimeException("Players can not be initialized from file, data missing");
                String name = details[1].trim();
                List<Integer> probabilities = fromDetails(details);
                players.add(new Player(name, team, probabilities));
                input = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong with player initialization from file.");
        }
        return players;
    }

    private List<Integer> fromDetails(String[] details) {
        List<Integer> probabilities = new ArrayList<>();
        for (int i = 2; i < details.length; i++) {
            probabilities.add(Integer.parseInt(details[i].trim()));
        }
        return probabilities;
    }

}
