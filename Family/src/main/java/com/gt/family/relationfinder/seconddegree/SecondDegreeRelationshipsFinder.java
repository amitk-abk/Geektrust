package com.gt.family.relationfinder.seconddegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.RelationshipsFinder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

abstract class SecondDegreeRelationshipsFinder implements RelationshipsFinder {

    String relationshipFrom(List<Person> people, Predicate<Person> predicate) {
        String result = people
                .stream()
                .filter(predicate)
                .map(Person::getName)
                .collect(Collectors.joining(" "));

        return result.length() > 0 ? result : "NONE";
    }

    List<Person> paternalAuntsAndUnclesOf(Person person) {
        Person mother = person.getFather();
        Person grandFather = mother.getFather();
        Optional<Family> grandFamily = grandFather.getFamily();
        if (grandFamily.isPresent())
            return grandFamily.get().getChildren();
        else
            return Collections.emptyList();
    }

    List<Person> maternalAuntsAndUnclesOf(Person person) {
        Person mother = person.getMother();
        Person grandMother = mother.getMother();
        Optional<Family> grandFamily = grandMother.getFamily();
        if (grandFamily.isPresent())
            return grandFamily.get().getChildren();
        else
            return Collections.emptyList();
    }

}
