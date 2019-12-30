package com.gt.family.relationfinder.thirddegree;

import com.gt.family.family.Person;

import java.util.Optional;

import static com.gt.family.family.Gender.FEMALE;
import static com.gt.family.family.Gender.MALE;

public class BrotherInLawFinder extends ThirdDegreeRelationshipsFinder {

    boolean asRequiredFromWifeSide(Person wife, Person p) {
        return p != wife && p.getGender().equals(MALE);
    }

    boolean asRequiredFromHusbandSide(Person husband, Person p) {
        return p != husband && p.getGender().equals(MALE);
    }

    protected Optional<Person> getSpouseOf(Person p) {
        return p.getHusband();
    }

    protected boolean asRequiredFromSiblingSide(Person person, Person p) {
        return p != person && p.getGender().equals(FEMALE);
    }
}
