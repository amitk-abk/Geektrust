package com.gt.cricket.game;

import com.gt.cricket.player.Player;
import com.gt.cricket.team.Team;

import java.util.HashMap;
import java.util.Map;

public class Scorer {

    private Map<Player, PlayerMatchStats> playerMatchStatsMap = new HashMap<>();
    private Map<Team, TeamStats> teamStatsMap = new HashMap<>();

    public void updatePlayedDeliveriesOf(Player onStrike) {
        playerMatchStatsMap.computeIfAbsent(onStrike, player -> {
            return new PlayerMatchStats(onStrike);
        });

        playerMatchStatsMap.computeIfPresent(onStrike, (playerName, playerMatchStats) -> {
            playerMatchStats.increaseFacedDeliveries();
            return playerMatchStats;
        });
    }

    public void updatePlayedDeliveryResult(String result, Player onStrike) {
        if (isNotOut(result)) {
            playerMatchStatsMap.computeIfPresent(onStrike, (playerName, playerMatchStats) -> {
                playerMatchStats.addToRunsScored(Integer.parseInt(result));
                return playerMatchStats;
            });
        }
    }

    private boolean isNotOut(String result) {
        return !result.equals("out");
    }

    public void batsmanOut(Player onStrike, Team battingTeam) {
        playerMatchStatsMap.computeIfPresent(onStrike, (playerName, playerMatchStats) -> {
            playerMatchStats.isOut();
            return playerMatchStats;
        });

        teamStatsMap.computeIfPresent(battingTeam, (team, teamStats) -> {
            teamStats.increaseWicketFallen();
            return teamStats;
        });
    }

    public int targetNowIs(int originalTarget, Team team) {
        int now = originalTarget - scoreOf(team);
        return now >= 0 ? now : 0;
    }

    public String getScoreCardOf(Team team) {
        StringBuilder builder = new StringBuilder();
        for (Player player : team.getPlayers())
            builder.append(playerCaption(player))
                    .append(runsScoredInInnings(player))
                    .append(ifNotOut(player))
                    .append(deliveriesFacedInInnings(player))
                    .append("\n");

        return builder.toString();
    }

    private String playerCaption(Player player) {
        return player.getName() + " - ";
    }

    private String runsScoredInInnings(Player player) {
        PlayerMatchStats matchStats = playerMatchStatsMap.get(player);
        if (matchStats != null && matchStats.isBatted)
            return String.valueOf(matchStats.getRunsScored());
        return "";
    }

    private String deliveriesFacedInInnings(Player player) {
        PlayerMatchStats matchStats = playerMatchStatsMap.get(player);
        if (matchStats != null)
            return "(" + matchStats.getDeliveriesFaced() + ")";
        return "DNB";
    }

    private String ifNotOut(Player player) {
        PlayerMatchStats matchStats = playerMatchStatsMap.get(player);
        if (matchStats != null) {
            return matchStats.isNotOut() ? "*" : "";
        }
        return "";
    }

    public int remainingDeliveriesInInnings(int availableDeliveries, Team team) {
        return availableDeliveries - totalPlayedBy(team);
    }

    private int totalPlayedBy(Team team) {
        int sum = 0;
        for (Player player : team.getPlayers()) {
            PlayerMatchStats stats = playerMatchStatsMap.get(player);
            if (stats != null) {
                sum += stats.getDeliveriesFaced();
            }
        }
        return sum;
    }


    public void updateTeamScoreWith(String result, Team battingTeam) {
        teamStatsMap.computeIfAbsent(battingTeam, team -> {
            return new TeamStats(battingTeam);
        });
        if (isNotOut(result)) {
            teamStatsMap.computeIfPresent(battingTeam, (team, teamStats) -> {
                teamStats.addScore(Integer.parseInt(result));
                return teamStats;
            });
        }
    }

    public int scoreOf(Team team) {
        return teamStatsMap.get(team).getScore();
    }

    public int remainingWicketsOf(Team team) {
        return team.getPlayers().size() - (teamStatsMap.get(team).getWicketsFallen() + 1);
    }

    public String getSecondInningsSummary(Team team, String result, int target, int maxOvers) {
        int totalScore = scoreOf(team);
        switch (result) {
            case "tie": return tied();
            case "lost": return lost(team, target, totalScore);
            case "wins": return winsTb(team, maxOvers);
            default: return lost(team, target, totalScore);
        }
    }

    private int margin(int target, int totalScore) {
        return target - 1 - totalScore;
    }

    private int remainingDeliveries(int maxOvers, Team team) {
        return remainingDeliveriesInInnings(maxOvers * 6, team);
    }

    public void batsmanOnCreese(Player batsman) {
        playerMatchStatsMap.computeIfAbsent(batsman, player -> {
            return new PlayerMatchStats(batsman);
        });
    }

    public boolean isTie(Team team, int target, int maxOvers) {
        return (scoreOf(team) == target - 1) &&
                (isInningsOverFor(team, maxOvers));
    }

    private boolean isInningsOverFor(Team team, int maxOvers) {
        return (remainingDeliveries(maxOvers, team) == 0 || remainingWicketsOf(team) == 0 );
    }

    private String wins(Team team, int maxOvers) {
        return team.getName() + " won by "
                + remainingWicketsOf(team) + " wicket and "
                + remainingDeliveries(maxOvers, team) + " balls remaining\n";
    }

    private String winsTb(Team team, int maxOvers) {
        return team.getName() + " won with "
                + remainingDeliveries(maxOvers, team) + " balls remaining\n";
    }


    private String lost(Team team, int target, int totalScore) {
        return team.getName() + " lost by " + margin(target, totalScore) + " runs\n";
    }

    private String tied() {
        return "It's a tie!!\n";
    }

    public String getSummaryOfFirstInnings(Team battingFirst) {
        int score = scoreOf(battingFirst);
        return battingFirst.getName() + " scored " + score + ", target to win " + (score + 1 + "\n");
    }

    private class PlayerMatchStats {

        private Player player;
        private int runsScored = 0;
        private int deliveriesFaced = 0;
        private boolean notOut;
        private boolean isBatted;

        PlayerMatchStats(Player player) {
            this.player = player;
            this.notOut = true;
            this.isBatted = true;
        }

        int getRunsScored() {
            return runsScored;
        }

        int getDeliveriesFaced() {
            return deliveriesFaced;
        }

        void increaseFacedDeliveries() {
            this.deliveriesFaced += 1;
        }

        void addToRunsScored(int runs) {
            this.runsScored += runs;
        }

        boolean isNotOut() {
            return notOut;
        }

        void isOut() {
            this.notOut = false;
        }

        public boolean isBatted() {
            return isBatted;
        }
    }


    private class TeamStats {
        private Team team;
        private int score;
        private int wicketsFallen;

        TeamStats(Team team) {
            this.team = team;
            this.score = 0;
            this.wicketsFallen = 0;
        }

        void addScore(int runs) {
            this.score += runs;
        }

        int getScore() {
            return score;
        }

        int getWicketsFallen() {
            return wicketsFallen;
        }

        void increaseWicketFallen() {
            this.wicketsFallen += 1;
        }
    }
}
