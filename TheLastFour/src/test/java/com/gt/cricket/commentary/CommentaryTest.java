package com.gt.cricket.commentary;

import com.gt.cricket.game.Batting;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOdd;
import com.gt.cricket.teams.TeamOddEven;
import com.gt.cricket.teams.TeamOut;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class CommentaryTest {

    private final int target = 40;
    private final int maxOvers = 4;

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
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Batting batting = batting(team, commentary);

        batting.playDelivery(0, 1);
        assertCommentaryHas(commentary.completeCommentary(), "0.1 First scores", 0);
    }

    private Team getOddEvenScoringTeam() {
        return new TeamOddEven();
    }


    @Test
    public void shouldDisplayPlayAndStrikeRotationInCommentary() throws Exception {
        Team team = getOddEvenScoringTeam();
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Batting batting = batting(team, commentary);

        batting.playDelivery(0, 1);
        assertCommentaryHas(commentary.completeCommentary(), "0.1 First scores", 0);

        batting.playDelivery(0, 2);
        assertCommentaryHas(commentary.completeCommentary(), "0.2 Second scores", 1);
    }

    @Test
    public void shouldAlwaysRotateStrikeWhenBatsmanScoreOddNumberOfRunsInCommentary() throws Exception {
        Team team = oddScoringTeam();
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Batting batting = batting(team, commentary);

        batting.playDelivery(1, 1);
        assertCommentaryHas(commentary.completeCommentary(), "1.1 First scores", 0);

        batting.playDelivery(1, 2);
        assertCommentaryHas(commentary.completeCommentary(), "1.2 Second scores", 1);

        batting.playDelivery(1, 3);
        assertCommentaryHas(commentary.completeCommentary(), "1.3 First scores", 2);

        batting.playDelivery(1, 4);
        assertCommentaryHas(commentary.completeCommentary(), "1.4 Second scores", 3);
    }

    private Team oddScoringTeam() {
        return new TeamOdd();
    }

    @Test
    public void shouldDisplayStrikeRotationInCommentaryForEvenRunOnLastBall() throws Exception {
        Team team = oddScoringTeam();
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Batting batting = batting(team, commentary);

        batting.playDelivery(1, 1);
        assertCommentaryHas(commentary.completeCommentary(), "1.1 First scores",0);

        batting.playDelivery(1, 2);
        assertCommentaryHas(commentary.completeCommentary(), "1.2 Second scores", 1);

        batting.playDelivery(1, 3);
        batting.playDelivery(1, 4);
        batting.playDelivery(1, 5);
        batting.playDelivery(1, 6);
        assertCommentaryHas(commentary.completeCommentary(), "1.6 Second scores", 5);

        batting.playDelivery(2, 1);
        assertCommentaryHas(commentary.completeCommentary(), "2.1 Second scores", 9);
    }

    @Test
    public void shouldDisplayBatmansDismisalInCommentaryWhenOnStrikeBatsmanIsOut() throws Exception {
        TeamOut team = new TeamOut();
        Commentary commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);

        Batting batting = batting(team, commentary);

        batting.playDelivery(1, 1);
        assertCommentaryHas(commentary.completeCommentary(), "1.1 First is out", 0);

        batting.playDelivery(1, 2);
        assertCommentaryHas(commentary.completeCommentary(), "1.2 Third scores", 1);

        batting.playDelivery(1, 3);
        assertCommentaryHas(commentary.completeCommentary(), "1.3 Second is out", 2);

        batting.playDelivery(1, 4);
        assertCommentaryHas(commentary.completeCommentary(), "1.4 Fourth is out", 3);
    }

    private Batting batting(Team team, Commentary commentary) {
        return new Batting
                .Of(team)
                .maxOvers(maxOvers)
                .scorer(scorer)
                .commentary(commentary)
                .init();
    }
}
