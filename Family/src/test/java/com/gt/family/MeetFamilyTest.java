package com.gt.family;

import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class MeetFamilyTest {

    @Test
    public void shouldProcessInputsFromFileAndProvideResult() throws Exception {
        String inputFileName = "./src/test/resources/input.txt";
        String testOutputFileName = "./src/test/resources/testoutput.txt";
        String expectedOutputFileName = "./src/test/resources/expectedoutput.txt";

        File testOutputFile = new File(testOutputFileName);
        if (testOutputFile.exists())
            testOutputFile.delete();

        PrintStream printStream = new PrintStream(new File(testOutputFileName));

        System.setOut(printStream);
        new MeetFamily(inputFileName).meet();

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
