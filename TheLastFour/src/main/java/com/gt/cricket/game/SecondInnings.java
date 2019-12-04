package com.gt.cricket.game;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.team.Team;

public class SecondInnings implements Innings {
    private Team team;
    private Commentary commentary;
    private int maxOvers;
    private int target;
    private Scorer scorer;

    private SecondInnings(Of of) {
        this.team = of.team;
        this.commentary = of.commentary;
        this.maxOvers = of.maxOvers;
        this.target = of.target;
        this.scorer = of.scorer;
    }

    @Override
    public String play() {
        Batting batting = secondInningBatting();
        return inningsResult(batting);
    }

    private Batting secondInningBatting() {
        return new Batting
                .Of(team)
                .maxOvers(maxOvers)
                .scorer(scorer)
                .commentary(commentary)
                .init();
    }

    private String inningsResult(Batting batting) {
        for (int over = 0; over < maxOvers; over++) {
            for (int delivery = 1; delivery <= 6; delivery++) {
                String result = batting.playDelivery(over, delivery);
                if (result.contains("Continue")) {
                    if (tie()) return "tie";
                    if (wins()) return "wins";
                }
                if (allOut(result))
                    return tie() ? "tie" : "lost";
            }
        }
        return "";
    }

    private boolean wins() {
        return scorer.scoreOf(team) >= target;
    }

    private boolean tie() {
        return scorer.isTie(team, target, maxOvers);
    }

    private boolean allOut(String result) {
        return result.equalsIgnoreCase("All out");
    }


    public static class Of {
        private Team team;
        private Commentary commentary;
        private int maxOvers;
        private int target;
        private Scorer scorer;

        public Of(Team team) {
            this.team = team;
        }

        public Of target(int target) {
            this.target = target;
            return this;
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

        public SecondInnings init() {
            return new SecondInnings(this);
        }
    }
}
