package com.gt.cricket.game;

import com.gt.cricket.commentary.Commentary;
import com.gt.cricket.game.Innings;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.game.SecondInnings;
import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOut;
import com.gt.cricket.teams.TeamSixes;
import com.gt.cricket.teams.TeamSkewed;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorerTest {

    private int target = 40;
    private Commentary commentary;
    private Scorer scorer = new Scorer();
    private int maxOvers = 4;


    @Test
    public void shouldDisplayInningsSummaryWhenLostChase() throws Exception {
        Team team = new TeamOut();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("TeamOut lost by 38 runs\n",
                scorer.getSecondInningsSummary(team, inningsResult, target, maxOvers));
    }

    @Test
    public void shouldDisplayTieWhenAllOutOnSameScore() throws Exception {
        set(2, 1);
        Team team = new TeamOut();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("It's a tie!!\n",
                scorer.getSecondInningsSummary(team, inningsResult, 2, 1));
        set(40, 2);
    }

    private void set(int t, int o) {
        target = t;
        maxOvers = o;
    }

    @Test
    public void shouldDisplayTieWhenScoresAreEqual() throws Exception {
        set(37, 1);
        Team team = new TeamSixes();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("It's a tie!!\n",
                scorer.getSecondInningsSummary(team, inningsResult, 37, 1));
        set(40, 2);
    }

    @Test
    public void shouldDisplayInningsSummaryWhenWonChase() throws Exception {
        Team team = new TeamSixes();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("TeamSixes won by 3 wicket and 17 balls remaining\n",
                scorer.getSecondInningsSummary(team, inningsResult, target, maxOvers));
    }

    @Test
    public void shouldDisplayPlayerAsDNBIfHeDidNotNeedToBatInInnings() throws Exception {
        TeamSixes team = new TeamSixes();
        Innings innings = inningsOf(team);

        innings.play();

        String expectedScoreCard = "First - 36*(6)\n" +
                "Second - 6*(1)\n" +
                "Third - DNB\n" +
                "Fourth - DNB\n";

        assertEquals(expectedScoreCard, scorer.getScoreCardOf(team));

    }

    @Test
    public void shouldDisplayCompleteScoreCard() throws Exception {
        TeamOut team = new TeamOut();
        Innings innings = inningsOf(team);

        innings.play();

        String expectedScoreCard = "First - 0(1)\n" +
                "Second - 0(1)\n" +
                "Third - 1*(1)\n" +
                "Fourth - 0(1)\n";

        assertEquals(expectedScoreCard, scorer.getScoreCardOf(team));
    }

    @Test
    public void shouldDisplayOpeningBatsmanNotOutOnZeroIfDoesnotGetToBat() throws Exception {
        Team team = new TeamSkewed();
        Innings innings = inningsOf(team);

        innings.play();

        String expectedScoreCard = "First - 0(1)\n" +
                "Second - 0*(0)\n" +
                "Third - 0(1)\n" +
                "Fourth - 0(1)\n";

        assertEquals(expectedScoreCard, scorer.getScoreCardOf(team));
    }

    private SecondInnings inningsOf(Team team) {
        commentary = new Commentary(team, scorer);
        commentary.inningsTarget(target);
        return new SecondInnings
                .Of(team)
                .commentary(commentary)
                .scorer(scorer)
                .target(target)
                .maxOvers(maxOvers)
                .init();
    }
}
