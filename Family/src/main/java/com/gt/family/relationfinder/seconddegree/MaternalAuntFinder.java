package com.gt.family.relationfinder.seconddegree;

import com.gt.family.family.Person;

import java.util.function.Predicate;

import static com.gt.family.family.Gender.FEMALE;

public class MaternalAuntFinder extends SecondDegreeRelationshipsFinder {

    @Override
    public String getRelationShip(Person person) {
        Predicate<Person> predicate = person1 -> person1.getGender().equals(FEMALE) && person1 != person.getMother();
        return relationshipFrom(maternalAuntsAndUnclesOf(person), predicate);
    }
}
