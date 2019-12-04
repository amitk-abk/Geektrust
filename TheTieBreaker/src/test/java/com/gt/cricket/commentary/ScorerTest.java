package com.gt.cricket.commentary;

import com.gt.cricket.game.Innings;
import com.gt.cricket.game.Scorer;
import com.gt.cricket.game.SecondInnings;
import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOut;
import com.gt.cricket.teams.TeamSixes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorerTest {

    private int target = 10;
    private Commentary commentary;
    private Scorer scorer = new Scorer();
    private int maxOvers = 1;


    @Test
    public void shouldDisplayInningsSummaryWhenLostChase() throws Exception {
        Team team = new TeamOut();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("TeamOut lost by 9 runs\n",
                scorer.getSecondInningsSummary(team, inningsResult, target, maxOvers));
    }

    @Test
    public void shouldDisplayTieWhenAllOutOnSameScore() throws Exception {
        set(1, 1);
        Team team = new TeamOut();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("It's a tie!!\n",
                scorer.getSecondInningsSummary(team, inningsResult, 2, 1));
        set(10, 1);
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
        set(10, 1);
    }

    @Test
    public void shouldDisplayInningsSummaryWhenWonChase() throws Exception {
        Team team = new TeamSixes();
        team.teamIsBattingsecond(true);
        Innings innings = inningsOf(team);

        String inningsResult = innings.play();

        assertEquals("TeamSixes won with 4 balls remaining\n",
                scorer.getSecondInningsSummary(team, inningsResult, target, maxOvers));
    }

    @Test
    public void shouldDisplayPlayerAsDNBIfHeDidNotNeedToBatInInnings() throws Exception {
        TeamSixes team = new TeamSixes();
        Innings innings = inningsOf(team);

        innings.play();

        String expectedScoreCard = "First - 12*(2)\n" +
                "Second - 0*(0)\n";

        assertEquals(expectedScoreCard, scorer.getScoreCardOf(team));

    }

    @Test
    public void shouldDisplayCompleteScoreCard() throws Exception {
        TeamOut team = new TeamOut();
        Innings innings = inningsOf(team);

        innings.play();

        String expectedScoreCard = "First - 0(1)\n" +
                "Second - 0*(0)\n";

        assertEquals(expectedScoreCard, scorer.getScoreCardOf(team));
    }

    private SecondInnings inningsOf(Team team) {
        commentary = new CommentaryForTieBreaker(team, scorer);
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
