package com.gt.tot;

import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BidsToRuleSoutherosTest {

    private BidsToRuleSoutheros bidsToRuleSoutheros = new BidsToRuleSoutheros();

    @Test
    public void shouldSendSecreteMessagesAndRequestAlliancesIntegration() throws Exception {
        String inputFileName = "./src/test/resources/input.txt";
        String testOutputFileName = "./src/test/resources/testoutput.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutput.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));

        System.setOut(printStream);
        bidsToRuleSoutheros.kingShanBidsToRuleSoutheros(inputFileName);

        String result = readFromFile(testOutputFileName);
        String expected = readFromFile(expectedOutputFileName);

        assertEquals(expected, result);
    }

    private String readFromFile(String testOutputFile) throws IOException {
        String result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(testOutputFile)))) {
            result = reader.lines().collect(Collectors.joining("\n"));
        }
        return result;
    }
}
