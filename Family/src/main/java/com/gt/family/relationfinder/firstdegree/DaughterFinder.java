package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;

import java.util.Optional;
import java.util.function.Predicate;

import static com.gt.family.family.Gender.FEMALE;

public class DaughterFinder extends FirstDegreeRelationshipsFinder {

    Optional<Family> familyFrom(Person person) {
        return person.getFamily();
    }

    Predicate<Person> isRequiredPerson(Person person) {
        return person1 -> person1.getGender().equals(FEMALE);
    }
}
