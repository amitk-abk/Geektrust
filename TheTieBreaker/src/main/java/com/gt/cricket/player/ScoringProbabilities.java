package com.gt.cricket.player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoringProbabilities {

    private static final Map<String, List<Integer>> scoringProbs = new HashMap<>();

    private ScoringProbabilities() {
        scoringProbs.put("Kirat Boli", Arrays.asList(5, 10, 25, 10, 25, 1, 14, 10));
        scoringProbs.put("N.S Nodhi", Arrays.asList(5, 15, 15, 10, 20, 1, 19, 15));
        scoringProbs.put("DB Vellyers", Arrays.asList(5, 10, 25, 10, 25, 1, 14, 10));
        scoringProbs.put("H Mamla", Arrays.asList(10, 15, 15, 10, 20, 1, 19, 10));
    }

    private static final ScoringProbabilities SCORING_PROBABILITIES = new ScoringProbabilities();

    public static ScoringProbabilities ofPlayers() {
        return SCORING_PROBABILITIES;
    }

    public List<Integer> of(String playerName) {
        return scoringProbs.getOrDefault(playerName, Arrays.asList(10,10,10,10,10,10,10,30));
    }
}
