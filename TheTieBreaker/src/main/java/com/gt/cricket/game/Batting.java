package com.gt.cricket.game;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.player.Player;
import com.gt.cricket.team.Team;

public class Batting {

    private int maxOvers;
    private Team team;
    private Player first;
    private Player second;
    private Player onStrike;
    private Commentary commentary;
    private Scorer scorer;

    private Batting(Of of) {
        this.team = of.team;
        this.maxOvers = of.maxOvers;
        this.scorer = of.scorer;
        this.commentary = of.commentary;

        this.first = team.startingBatsmen().get(0);
        this.onStrike = this.first;

        this.second = team.startingBatsmen().get(1);
        this.second.setOffStrike();

        this.scorer.batsmanOnCreese(this.first);
        this.scorer.batsmanOnCreese(this.second);
    }

    public String playDelivery(int over, int delivery) {
        String result = strikerPlaysDelivery();
        updateStats(result);
        updateCommentary(over, delivery, result);

        if (isOut(result)) {
            updateDismissal();
            nextBatsman();
            if (allOut()) {
                return "All out";
            }
        }
        doStrikeRotationIfRequired(delivery, result);
        return "Continue";
    }

    private String strikerPlaysDelivery() {
        return onStrike.playsDelivery();
    }

    private void updateStats(String result) {
        scorer.updatePlayedDeliveriesOf(onStrike);
        scorer.updatePlayedDeliveryResult(result, onStrike);
        scorer.updateTeamScoreWith(result, team);
    }

    private void updateCommentary(int over, int delivery, String result) {
        commentary.commentaryOnDelivery(onStrike.getName(), result, over, delivery);
        if (delivery == 6)
            commentary.onOverCompletion(over, maxOvers);
    }

    private boolean isOut(String result) {
        return result.equals("out");
    }

    private void updateDismissal() {
        scorer.batsmanOut(onStrike, team);
    }

    private void nextBatsman() {
        int id = strikerBatsmanId();
        onStrike = nextBatsmanOnStrike();
        setOnStrikeBatsmanFrom(id);
    }


    private Player nextBatsmanOnStrike() {
        return team.nextBatsman();
    }

    private void doStrikeRotationIfRequired(int delivery, String result) {
        if (shouldRotateStrike(result, delivery))
            rotateStrike();
    }

    private boolean allOut() {
        return onStrike == null;
    }

    private int strikerBatsmanId() {
        return onStrike.equals(first) ? 1 : 2;
    }

    private void setOnStrikeBatsmanFrom(int id) {
        if (onStrike != null)
            scorer.batsmanOnCreese(onStrike);
        if (id == 1)
            first = onStrike;
        else
            second = onStrike;
    }


    private boolean isNotOut(String result) {
        return !isOut(result);
    }

    private boolean shouldRotateStrike(String result, int ball) {
        if (isNotOut(result)) {
            if (oddRunTakenOnNonLastBall(result, ball)) return true;
            if (oddRunTakenOnLastBall(result, ball)) return false;
            if (evenRunTakenOnNonLastBall(result, ball)) return false;
            return evenRunTakenOnLastBall(result, ball);
        } else {
            return ball == 6;
        }
    }

    private boolean evenRunTakenOnLastBall(String result, int ball) {
        return (isEvenRun(result) && ball == 6);
    }

    private boolean evenRunTakenOnNonLastBall(String result, int ball) {
        return (isEvenRun(result) && ball != 6);
    }

    private boolean oddRunTakenOnLastBall(String result, int ball) {
        return (isOddRun(result) && ball == 6);
    }

    private boolean oddRunTakenOnNonLastBall(String result, int ball) {
        return (isOddRun(result) && ball != 6);
    }

    private boolean isEvenRun(String result) {
        return !isOddRun(result);
    }

    private void rotateStrike() {
        first.rotateStrike();
        second.rotateStrike();
        onStrike = first.isOnStrike() ? first : second;
    }

    private boolean isOddRun(String result) {
        return Integer.valueOf(result) % 2 != 0;
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

        public Of maxOvers(int maxOvers) {
            this.maxOvers = maxOvers;
            return this;
        }

        public Of scorer(Scorer scorer) {
            this.scorer = scorer;
            return this;
        }

        public Batting init() {
            return new Batting(this);
        }
    }
}
