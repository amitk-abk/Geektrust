package com.gt.cricket;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.commentary.CommentaryForTieBreaker;
import com.gt.cricket.game.FirstInnings;
import com.gt.cricket.game.Innings;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.game.SecondInnings;
import com.gt.cricket.team.Team;

public class TieBreaker {
    private Team battingFirst;
    private Team battingSecond;
    private final int maxOvers = 1;
    private Scorer scorer;

    public TieBreaker(Team battingFirst, Team battingSecond) {
        this.battingFirst = battingFirst;
        this.battingSecond = battingSecond;
        scorer = new Scorer();
    }

    public void start() {
        Commentary firstInningsCommentary = new CommentaryForTieBreaker(battingFirst, scorer);
        int target = fromFirstInnings(firstInningsCommentary);

        Commentary secondInningsCommentary = new CommentaryForTieBreaker(battingSecond, scorer);
        String result = targetChase(target, secondInningsCommentary);

        System.out.println(scorer.getSecondInningsSummary(battingSecond, result, target, maxOvers));

        System.out.println(battingFirst.getName());
        System.out.println(scorer.getScoreCardOf(battingFirst));

        System.out.println(battingSecond.getName());
        System.out.println(scorer.getScoreCardOf(battingSecond));

        System.out.println(battingFirst.getName() + " innings:");
        System.out.println(firstInningsCommentary.completeCommentary() + "\n");

        System.out.println(battingSecond.getName() + " innings:");
        System.out.println(secondInningsCommentary.completeCommentary());

    }

    private int fromFirstInnings(Commentary commentary) {
        Innings innings = new FirstInnings
                .Of(battingFirst)
                .scorer(scorer)
                .commentary(commentary)
                .maxOvers(maxOvers)
                .init();

        innings.play();
        return scorer.scoreOf(battingFirst) + 1;
    }

    private String targetChase(int target, Commentary commentary) {
        commentary.inningsTarget(target);
        Innings innings = new SecondInnings
                .Of(battingSecond)
                .target(target)
                .scorer(scorer)
                .commentary(commentary)
                .maxOvers(maxOvers)
                .init();

        return innings.play();
    }
}
