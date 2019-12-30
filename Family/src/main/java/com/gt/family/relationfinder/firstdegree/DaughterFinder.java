package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;

import java.util.Optional;
import java.util.function.Predicate;

import static com.gt.family.family.Gender.FEMALE;

public class DaughterFinder extends FirstDegreeRelationshipsFinder {
    public DaughterFinder() {
        super();
    }

    @Override
    public String getRelationShip(Person person) {

        Optional<Family> personFamily = getFamilyFrom(person);
        if (!personFamily.isPresent()) {
            return "NONE";
        }

        Predicate<Person> predicate = person1 -> person1.getGender().equals(FEMALE);
        Family family = personFamily.get();

        return relationshipFrom(predicate, family);
    }


    @Override
    protected Optional<Family> getFamilyFrom(Person person) {
        return person.getFamily();
    }
}
