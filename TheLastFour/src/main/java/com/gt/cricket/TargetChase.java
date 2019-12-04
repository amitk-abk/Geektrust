package com.gt.cricket;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.game.Innings;
import com.gt.cricket.game.SecondInnings;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.team.Team;

public class TargetChase {
    private Team team;
    private int maxOvers;
    private int target;
    private Scorer scorer;

    private TargetChase(By by) {
        this.team = by.team;
        this.maxOvers = by.maxOvers;
        this.target = by.target;
        this.scorer = new Scorer();
    }

    private void chase() {
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Innings innings = new SecondInnings
                .Of(team)
                .target(target)
                .scorer(scorer)
                .commentary(commentary)
                .maxOvers(maxOvers)
                .init();

        String result = innings.play();

        String summary = scorer.getSecondInningsSummary(team, result, target, maxOvers);

        System.out.println(summary);
        System.out.println(scorer.getScoreCardOf(team));
        System.out.println(maxOvers + " overs left. " + target + " runs to win\n");
        System.out.println(commentary.completeCommentary());
    }

    static class By {
        private Team team;
        private int maxOvers;
        private int target;

        By(Team team) {
            this.team = team;
        }

        By targetOf(int target){
            this.target = target;
            return this;
        }

        By inOvers(int maxOvers) {
            this.maxOvers = maxOvers;
            return this;
        }

        void begin() {
            new TargetChase(this).chase();
        }
    }
}
