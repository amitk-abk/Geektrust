package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.RelationshipsFinder;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class FirstDegreeRelationshipsFinder implements RelationshipsFinder {

    abstract Predicate<Person> isRequiredPerson(Person person);

    abstract Optional<Family> familyFrom(Person person);

    @Override
    public String getRelationShip(Person person) {
        Optional<Family> family = familyFrom(person);
        if (!family.isPresent())
            return "NONE";

        return relationshipFrom(isRequiredPerson(person), family.get());
    }


    private String relationshipFrom(Predicate<Person> requiredPerson, Family family) {
        String result = family
                .getChildren()
                .stream()
                .filter(requiredPerson)
                .map(Person::getName)
                .collect(Collectors.joining(" "));

        return result.length() > 0 ? result : "NONE";
    }
}
