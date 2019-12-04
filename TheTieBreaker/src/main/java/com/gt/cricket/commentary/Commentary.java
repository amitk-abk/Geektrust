package com.gt.cricket.commentary;

import com.gt.cricket.game.Scorer;
import com.gt.cricket.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Commentary {

    protected List<String> matchCommentary = new ArrayList<>();
    protected int target;
    protected Scorer scorer;
    protected Team team;

    Commentary(Team team, Scorer scorer) {
        this.team = team;
        this.scorer = scorer;
    }

    public String completeCommentary() {
        return matchCommentary.stream().collect(Collectors.joining("\n"));
    }

    public void commentaryOnDelivery(String playerName, String result, int over, int ball) {
        if (result.equals("out"))
            this.outCommentary(playerName, over, ball);
        else
            this.scoreCommentary(playerName, over, ball, result);
    }

    protected void outCommentary(String playerName, int over, int ball) {
        matchCommentary.add(over + "." + ball + " " + playerName + " is out!");
    }

    protected void scoreCommentary(String playerName, int over, int ball, String runs) {
        matchCommentary.add(over + "." + ball + " " + playerName + " scores " + runs + " run");
    }

    public void onOverCompletion(int over, int maxOvers) {
        matchCommentary.add(this.overCompletionCommentary(over, maxOvers));
    }

    protected String overCompletionCommentary(int over, int maxOvers) {
        String commentary = (maxOvers - (over + 1)) + " overs left.";
        if (team.isTeamBattingSecond())
            commentary += " " + scorer.targetNowIs(target, team) + " runs to win";
        return "\n" + commentary + "\n";
    }

    public void inningsTarget(int target) {
        this.target = target;
    }
}
