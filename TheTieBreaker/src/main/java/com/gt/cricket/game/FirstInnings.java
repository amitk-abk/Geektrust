package com.gt.cricket.game;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.team.Team;

public class FirstInnings implements Innings {

    private Team team;
    private Commentary commentary;
    private int maxOvers;
    private Scorer scorer;

    private FirstInnings(Of of) {
        this.team = of.team;
        this.commentary = of.commentary;
        this.maxOvers = of.maxOvers;
        this.scorer = of.scorer;
    }

    @Override
    public String play() {
        return setTargetToChase();
    }

    private String setTargetToChase() {
        Batting batting = new Batting
                .Of(team)
                .maxOvers(maxOvers)
                .scorer(scorer)
                .commentary(commentary)
                .init();

        for (int over = 0; over < maxOvers; over++) {
            for (int delivery = 1; delivery <=6; delivery++) {
                String result = batting.playDelivery(over, delivery);
                if (allOut(result)) return "completed";
            }
        }
        return "";
    }

    private boolean allOut(String result) {
        return result.equalsIgnoreCase("All out");
    }

    public static class Of {
        private Team team;
        private Commentary commentary;
        private int maxOvers;
        private Scorer scorer;

        public Of(Team team) {
            this.team = team;
        }

        public Of commentary(Commentary commentary) {
            this.commentary = commentary;
            return this;
        }

        public Of scorer(Scorer scorer) {
            this.scorer = scorer;
            return this;
        }

        public Of maxOvers(int maxOvers) {
            this.maxOvers = maxOvers;
            return this;
        }

        public FirstInnings init() {
            return new FirstInnings(this);
        }
    }
}
