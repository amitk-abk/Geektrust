package com.gt.family.relationfinder.firstdegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;

import java.util.Optional;
import java.util.function.Predicate;

public class SiblingsFinder extends FirstDegreeRelationshipsFinder {

    @Override
    public String getRelationShip(Person person) {

        Optional<Family> motherFamily = getFamilyFrom(person);
        if (!motherFamily.isPresent()) {
            return "NONE";
        }

        Predicate<Person> predicate = kid -> !kid.getName().equals(person.getName());
        Family family = motherFamily.get();
        if (family.getChildren().size() <= 0)
            return "NONE";

        return relationshipFrom(predicate, family);
    }

    @Override
    protected Optional<Family> getFamilyFrom(Person person) {
        return person.getMother().getFamily();
    }
}
