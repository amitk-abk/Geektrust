package com.gt.family;

import com.gt.family.family.Gender;
import com.gt.family.tree.FamilyTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class MeetFamily {

    private final String file;
    private final FamilyTree familyTree = new FamilyTree();

    MeetFamily(String file) {
        this.file = file;
    }

    void meet() {
        try (Stream<String> fileStream = Files.lines(Paths.get(file))) {
            fileStream
                    .filter(line -> !line.isEmpty())
                    .forEach(this::processInputLine);
        } catch (IOException e) {
            System.out.println("Something wrong happened with input file: " + e.getMessage());
        }
    }

    private void processInputLine(String line) {
        String[] input = line.split(Pattern.quote(" "));
        String command = input[0];
        if (command.equals("ADD_CHILD"))
            addChild(input);

        if (command.equals("GET_RELATIONSHIP"))
            getRelationship(input);

    }

    private void addChild(String[] input) {
        String gender = input[input.length - 1];
        String childName = input[input.length - 2];
        StringBuilder sb = new StringBuilder(input[1]);
        for (int i = 2 ; i < input.length - 2; i++) {
            sb.append(" ").append(input[i]);
        }
        String motherName = sb.toString().trim();
        System.out.println(familyTree.addToFamilyTree(motherName, childName, genderFrom(gender)));
    }

    private void getRelationship(String[] input) {
        String relationShip = input[input.length - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < input.length - 1; i++) {
            sb.append(" ").append(input[i]);
        }
        String personName = sb.toString().trim();
        System.out.println(familyTree.getRelationShip(personName, relationShip));
    }

    private Gender genderFrom(String gender) {
        return Arrays.stream(Gender.values())
                .filter(gender1 -> gender1.toString().equals(gender))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
