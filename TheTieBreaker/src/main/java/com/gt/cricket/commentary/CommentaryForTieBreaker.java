package com.gt.cricket.commentary;

import com.gt.cricket.game.Scorer;
import com.gt.cricket.team.Team;

public class CommentaryForTieBreaker extends Commentary {

    public CommentaryForTieBreaker(Team team, Scorer scorer) {
        super(team, scorer);
    }

    @Override
    public void scoreCommentary(String playerName, int over, int ball, String runs) {
        String perBallCommentary = over + "." + ball + " " + playerName + " scores " + runs + " run ";
        if (team.isTeamBattingSecond()) {
            if (teamWins())
                perBallCommentary += team.getName() + " wins!";
            else {
                if (ball == 6)
                    perBallCommentary += exactStatus();
            }
        }
        matchCommentary.add(perBallCommentary);
    }

    @Override
    protected void outCommentary(String playerName, int over, int ball) {
        matchCommentary.add(over + "." + ball + " " + playerName + " is out! " + status());
    }

    @Override
    public String overCompletionCommentary(int over, int maxOvers) {
        return "";
    }

    private String status() {
        if (team.isTeamBattingSecond()) {
            return exactStatus();
        }
        return team.getName() + " all out";
    }

    private boolean isTie() {
        return scorer.isTie(this.team, this.target, 1);
    }

    private String exactStatus() {
        if (teamWins()) {
            return team.getName() + " wins!!";
        } else if (isTie()) {
            return "It is a tie!!";
        } else {
            return team.getName() + " lost!";
        }
    }

    private boolean teamWins() {
        return scorer.scoreOf(team) >= target;
    }
}
