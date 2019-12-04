package com.gt.cricket.commentary;

import com.gt.cricket.game.Batting;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOdd;
import com.gt.cricket.teams.TeamOddEven;
import com.gt.cricket.teams.TeamOut;
import com.gt.cricket.teams.TeamSixes;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommentaryTest {

    private final int target = 10;
    private final int maxOvers = 1;

    private final Scorer scorer = new Scorer();

    private void assertCommentaryHas(String battingCommentary, String expected, int location) {
        List<String> commentaries = Arrays.asList(battingCommentary.split(Pattern.quote("\n")));
        assertTrue(commentaryHas(commentaries.get(location), expected));
    }

    private boolean commentaryHas(String battingCommentary, String expected) {
        return battingCommentary.contains(expected);
    }

    @Test
    public void shouldPlayAndGetRecordedInCommentary() throws Exception {
        Team team = getOddEvenScoringTeam();
        Commentary commentaryForTieBreaker = new CommentaryForTieBreaker(team, scorer);
        commentaryForTieBreaker.inningsTarget(target);

        Batting batting = batting(team, commentaryForTieBreaker);

        batting.playDelivery(0, 1);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "0.1 First scores", 0);
    }

    private Batting batting(Team team, Commentary commentaryForTieBreaker) {
        return new Batting.Of(team)
                .maxOvers(maxOvers)
                .scorer(scorer)
                .commentary(commentaryForTieBreaker)
                .init();
    }

    private Team getOddEvenScoringTeam() {
        return new TeamOddEven();
    }


    @Test
    public void shouldDisplayPlayAndStrikeRotationInCommentary() throws Exception {
        Team team = getOddEvenScoringTeam();
        Commentary commentaryForTieBreaker = new CommentaryForTieBreaker(team, scorer);

        Batting batting = batting(team, commentaryForTieBreaker);

        batting.playDelivery(0, 1);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "0.1 First scores", 0);

        batting.playDelivery(0, 2);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "0.2 Second scores", 1);
    }

    @Test
    public void shouldAlwaysRotateStrikeWhenBatsmanScoreOddNumberOfRunsInCommentary() throws Exception {
        Team team = oddScoringTeam();
        CommentaryForTieBreaker commentaryForTieBreaker = new CommentaryForTieBreaker(team, scorer);

        Batting batting = batting(team, commentaryForTieBreaker);

        batting.playDelivery(1, 1);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "1.1 First scores", 0);

        batting.playDelivery(1, 2);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "1.2 Second scores", 1);

        batting.playDelivery(1, 3);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "1.3 First scores", 2);

        batting.playDelivery(1, 4);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(), "1.4 Second scores", 3);
    }

    private Team oddScoringTeam() {
        return new TeamOdd();
    }


    @Test
    public void shouldDisplayBatmansDismisalInCommentaryWhenOnStrikeBatsmanIsOut() throws Exception {
        TeamOut team = new TeamOut();
        CommentaryForTieBreaker commentaryForTieBreaker = new CommentaryForTieBreaker(team, scorer);

        Batting batting = batting(team, commentaryForTieBreaker);

        batting.playDelivery(1, 1);
        assertEquals(commentaryForTieBreaker.completeCommentary(), "1.1 First is out! TeamOut all out");
    }

    @Test
    public void shouldDisplayTeamWinningInCommentaryWhenChasingTeamWins() throws Exception {
        Team team = new TeamSixes();
        team.teamIsBattingsecond(true);
        CommentaryForTieBreaker commentaryForTieBreaker = new CommentaryForTieBreaker(team, scorer);
        commentaryForTieBreaker.inningsTarget(target);
        Batting batting = batting(team, commentaryForTieBreaker);

        batting.playDelivery(1, 1);
        batting.playDelivery(1, 2);
        assertCommentaryHas(commentaryForTieBreaker.completeCommentary(),
                "1.2 First scores 6 run TeamSixes wins!", 1);
    }
}
