package com.gt.cricket.player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Scoring {

    private static final Scoring SCORING = new Scoring();
    private final List<String> outComes = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "out");
    private final Map<Player, List<Integer>> cumulativesOfPlayers = new ConcurrentHashMap<>();

    private Scoring() {
    }

    public static Scoring offDelivery() {
        return SCORING;
    }

    public String byPlayer(Player player, int scoreIndex) {
        cumulativesOfProbabilitiesOf(player);
        int score = getConformingProbabilityIndex(player, scoreIndex);
        return outComes.get(score);
    }

    private void cumulativesOfProbabilitiesOf(Player player) {
        cumulativesOfPlayers.putIfAbsent(player, buildCumulativeFor(player));
    }

    private int getConformingProbabilityIndex(Player player, int candidate) {
        int search = Collections.binarySearch(cumulativesOfPlayers.get(player), candidate);
        return search < 0 ? (search + 1) * -1 : search;
    }

    private List<Integer> buildCumulativeFor(Player player) {
        List<Integer> probabilities = player.getScoringProbabilities();
        List<Integer> cumulatives = new ArrayList<>();
        for (int j = 0; j < probabilities.size(); j++) {
            int cumulative = 0;
            for (int i = 0; i <= j; i++) {
                cumulative += probabilities.get(i);
            }
            cumulatives.add(j, cumulative);
        }
        return cumulatives;
    }
}
