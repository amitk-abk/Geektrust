package com.gt.family.relationfinder.seconddegree;

import com.gt.family.family.Person;

import java.util.function.Predicate;

import static com.gt.family.family.Gender.MALE;

public class PaternalUncleFinder extends SecondDegreeRelationshipsFinder {

    @Override
    public String getRelationShip(Person person) {
        Predicate<Person> predicate = person1 -> person1.getGender().equals(MALE) && person1 != person.getFather();
        return relationshipFrom(paternalAuntsAndUnclesOf(person), predicate);
    }
}
