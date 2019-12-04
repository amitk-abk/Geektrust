package com.gt.cricket;

import com.gt.cricket.team.Team;
import com.gt.cricket.teams.TeamOnes;
import com.gt.cricket.teams.TeamOnes1;
import com.gt.cricket.teams.TeamSixes;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TieBreakerTest {

    @Test
    public void testForResultFirstWinIntegration() throws Exception {
        String testOutputFileName = "./src/test/resources/testoutputfw.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutputfw.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));
        System.setOut(printStream);

        Team first = new TeamSixes();
        Team second = new TeamOnes();
        second.teamIsBattingsecond(true);

        new TieBreaker(first, second).start();

        assertEquals(readFromFile(expectedOutputFileName), readFromFile(testOutputFileName));
    }

    @Test
    public void testForResultSecondWinIntegration() throws Exception {
        String testOutputFileName = "./src/test/resources/testoutputsw.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutputsw.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));
        System.setOut(printStream);

        Team first = new TeamOnes();
        Team second = new TeamSixes();
        second.teamIsBattingsecond(true);

        new TieBreaker(first, second).start();

        assertEquals(readFromFile(expectedOutputFileName), readFromFile(testOutputFileName));
    }

    @Test
    public void testForTieWhenScoresAreEqualsIntegration() throws Exception {
        String testOutputFileName = "./src/test/resources/testoutputtie.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutputtie.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));
        System.setOut(printStream);

        Team first = new TeamOnes();
        Team second = new TeamOnes1();
        second.teamIsBattingsecond(true);

        new TieBreaker(first, second).start();

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
