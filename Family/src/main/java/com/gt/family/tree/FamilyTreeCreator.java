package com.gt.family.tree;

import com.gt.family.family.Family;
import com.gt.family.family.Gender;
import com.gt.family.family.NullPerson;
import com.gt.family.family.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.gt.family.family.Gender.FEMALE;
import static com.gt.family.family.Gender.MALE;

public class FamilyTreeCreator {

    private String filePath;

    public FamilyTreeCreator(String filePath) {
        this.filePath = filePath;
    }

    private static Person person(String personName, Gender gender) {
        return new Person(personName, gender);
    }

    public Person familyTree() {
        Person familyHead = new NullPerson("Head", MALE);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath)))) {

            String input = reader.readLine().trim();
            while (!input.equalsIgnoreCase("q")) {

                if (input.length() > 0 && !input.contains(",")) {
                    String people[] = input.split(Pattern.quote("-"));
                    String king = people[0].split(Pattern.quote(":"))[0].trim();
                    String queen = people[1].split(Pattern.quote(":"))[0].trim();
                    familyHead = of(king, queen);
                    input = reader.readLine().trim();
                }

                if (input.length() > 0) {
                    if (input.contains(",") && input.contains("-")) {
                        String mother = input.split(Pattern.quote(","))[0].trim();
                        String couple = input.split(Pattern.quote(","))[1].trim();

                        String spouses[] = couple.split(Pattern.quote("-"));

                        Person first = personFrom(spouses[0]);
                        Person second = personFrom(spouses[1]);

                        addTo(familyHead, mother, first, second);

                    } else {
                        String mother = input.split(Pattern.quote(","))[0].trim();
                        String person = input.split(Pattern.quote(","))[1].trim();

                        Person p = personFrom(person);
                        addTo(familyHead, mother, p);
                    }
                }
                input = reader.readLine().trim();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return familyHead;
    }

    private Person of(String king, String queen) {
        Person headMan = person(king, MALE);
        Person headWoman = person(queen, FEMALE);

        headMan.setFather(new NullPerson("Null Father", MALE));
        headMan.setMother(new NullPerson("Null Mother", FEMALE));

        headWoman.setFather(new NullPerson("Null Father", MALE));
        headWoman.setMother(new NullPerson("Null Mother", FEMALE));

        new Family.Of(headMan).wife(headWoman).family();
        return headMan;
    }

    private Person personFrom(String spouses) {
        String name = spouses.split(Pattern.quote(":"))[0];
        String gender = spouses.split(Pattern.quote(":"))[1];
        return new Person(name, genderFrom(gender));
    }

    private void addTo(Person head, String mother, Person... familyMembers) {
        String result = addChild(head, mother, familyMembers[0]);
        if (!result.equals("CHILD_ADDITION_SUCCEEDED"))
            throw new RuntimeException("Family tree loading failed. Incorrect input.");

        if (familyMembers.length == 2) {
            familyOf(familyMembers);
        }
    }

    String addChild(Person head, String mother, Person familyMember) {
        Person person = FamilyTree.findPerson(head, mother);
        if (person == null)
            return "PERSON_NOT_FOUND";

        Optional<Family> family = person.getFamily();
        if (person.getGender().equals(MALE) || !family.isPresent())
            return "CHILD_ADDITION_FAILED";

        family.get().addChild(familyMember);
        return "CHILD_ADDITION_SUCCEEDED";
    }

    private void familyOf(Person[] familyMembers) {
        Person first = familyMembers[0];
        Person second = familyMembers[1];

        second.setFather(new NullPerson("Null Father", MALE));
        second.setMother(new NullPerson("Null Mother", MALE));

        Person husband = first.getGender().equals(MALE) ? first : second;
        Person wife = first.getGender().equals(FEMALE) ? first : second;

        new Family.Of(husband).wife(wife).family();
    }

    private Gender genderFrom(String gender) {
        return gender.equalsIgnoreCase("male") ? MALE : FEMALE;
    }
}
