package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;

import java.util.Optional;
import java.util.function.Predicate;

public class SiblingsFinder extends FirstDegreeRelationshipsFinder {

    Optional<Family> familyFrom(Person person) {
        return person.getMother().getFamily();
    }

    Predicate<Person> isRequiredPerson(Person person) {
        return kid -> !kid.getName().equals(person.getName());
    }
}
