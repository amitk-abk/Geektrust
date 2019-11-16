package com.gt.tot.message;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SecretsRepository {
    private final String repository;
    private final List<String> secrets;

    private SecretsRepository(String repository) {
        this.repository = repository;
        this.secrets = new ArrayList<>();
        loadRepository();
    }

    public static SecretsRepository from(String repository) {
        return new SecretsRepository(repository);
    }

    private void loadRepository() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(repository)))) {
            secrets.addAll(reader.lines()
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String secretMessage() {
        Random random = new Random();
        Collections.shuffle(secrets);
        return secrets.get(random.nextInt(secrets.size()));
    }
}
