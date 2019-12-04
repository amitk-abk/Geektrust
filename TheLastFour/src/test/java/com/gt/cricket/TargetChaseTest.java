package com.gt.cricket;

import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOnes;
import com.gt.cricket.teams.TeamSixes;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TargetChaseTest {

    @Test
    public void chaseTargetIntegrationTestWin() throws Exception {
        String testOutputFileName = "./src/test/resources/testoutput.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutput.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));
        System.setOut(printStream);

        Team team = new TeamSixes();
        team.teamIsBattingsecond(true);
        new TargetChase.By(team)
                .targetOf(40)
                .inOvers(4)
                .begin();

        assertEquals(readFromFile(expectedOutputFileName), readFromFile(testOutputFileName));
    }

    @Test
    public void chaseTargetIntegrationTestLost() throws Exception {
        String testOutputFileName = "./src/test/resources/testoutputlost.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutputlost.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));
        System.setOut(printStream);

        Team team = new TeamOnes();
        team.teamIsBattingsecond(true);
        new TargetChase.By(team)
                .targetOf(40)
                .inOvers(4)
                .begin();

        assertEquals(readFromFile(expectedOutputFileName), readFromFile(testOutputFileName));
    }

    private String readFromFile(String testOutputFile) throws IOException {
        String result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(testOutputFile)))) {
            result = reader.lines().collect(Collectors.joining("\n"));
        }
        return result;
    }
}
