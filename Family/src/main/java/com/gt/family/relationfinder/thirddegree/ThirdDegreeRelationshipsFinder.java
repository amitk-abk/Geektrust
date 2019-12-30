package com.gt.family.relationfinder.thirddegree;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.RelationshipsFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gt.family.family.Gender.FEMALE;

abstract class ThirdDegreeRelationshipsFinder implements RelationshipsFinder {

    public String getRelationShip(Person person) {
        List<String> names = new ArrayList<>();
        if (person.getGender().equals(FEMALE)) {
            names.addAll(siblingsInLawFromHusbandSide(person));
        } else {
            names.addAll(siblingsInLawFromWifeSide(person));
        }
        names.addAll(siblingsInLawFromSiblingsSide(person));

        if (names.size() <= 0)
            return "NONE";
        return String.join(" ", names);
    }

    private List<String> siblingsInLawFromHusbandSide(Person person) {
        List<String> names = new ArrayList<>();
        Optional<Person> oHusband = person.getHusband();
        if (oHusband.isPresent()) {
            Person husband = oHusband.get();
            Person motherInLaw = husband.getMother();
            Optional<Family> inLawsFamily = motherInLaw.getFamily();
            if (inLawsFamily.isPresent()) {
                Family inLaws = inLawsFamily.get();
                for (Person p : inLaws.getChildren()) {
                    if (asRequiredFromHusbandSide(husband, p))
                        names.add(p.getName());
                }
            }
        }
        return names;
    }

    private List<String> siblingsInLawFromWifeSide(Person person) {
        List<String> names = new ArrayList<>();
        Optional<Person> oWife = person.getWife();
        if (oWife.isPresent()) {
            Person wife = oWife.get();
            Person motherInLaw = wife.getMother();
            Optional<Family> inLawsFamily = motherInLaw.getFamily();
            if (inLawsFamily.isPresent()) {
                Family inLaws = inLawsFamily.get();
                for (Person p : inLaws.getChildren()) {
                    if (asRequiredFromWifeSide(wife, p))
                        names.add(p.getName());
                }
            }
        }
        return names;
    }

    private List<String> siblingsInLawFromSiblingsSide(Person person) {
        List<String> names = new ArrayList<>();
        Person mother = person.getMother();
        Optional<Family> myOFamily = mother.getFamily();
        if (myOFamily.isPresent()) {
            Family myFamily = myOFamily.get();
            for (Person p : myFamily.getChildren()) {
                if (asRequiredFromSiblingSide(person, p)) {
                    Optional<Person> personFromSiblings = getSpouseOf(p);
                    if (personFromSiblings.isPresent())
                        names.add(personFromSiblings.get().getName());
                }
            }
        }
        return names;
    }

    abstract Optional<Person> getSpouseOf(Person p);
    abstract boolean asRequiredFromSiblingSide(Person person, Person p);
    abstract boolean asRequiredFromHusbandSide(Person huband, Person p);
    abstract boolean asRequiredFromWifeSide(Person wife, Person p);
}
