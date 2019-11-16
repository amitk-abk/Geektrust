package com.gt.tot.message;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class SecretsRepositoryTest {

    private String s = "\"zmzmzmzaztzozh\",\n" +
            "\"Drag on Martin!\",\n" +
            "\"zmzmzmzaztzozh\",\n" +
            "\"Drag on Martin!\",\n" +
            "\"ooctopussssccut\",\n" +
            "\"Drag on Martin!\",\n" +
            "\"ooctopussss\",\n" +
            "\"gooorriillaag\"";

    @Test
    public void shouldGetSecretMessageFromRepository() throws Exception {
        List<String> messages = Arrays.asList(s.split(Pattern.quote("\n")));

        String secret = SecretsRepository.from("./src/test/resources/miniature.txt").secretMessage();

        assertTrue(messages.contains(secret));
    }
}
