package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.RelationshipsFinder;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class FirstDegreeRelationshipsFinder implements RelationshipsFinder {

    protected abstract Optional<Family> getFamilyFrom(Person person);

    String relationshipFrom(Predicate<Person> predicate, Family family) {
        String result = family
                .getChildren()
                .stream()
                .filter(predicate)
                .map(Person::getName)
                .collect(Collectors.joining(" "));

        return result.length() > 0 ? result : "NONE";
    }
}
