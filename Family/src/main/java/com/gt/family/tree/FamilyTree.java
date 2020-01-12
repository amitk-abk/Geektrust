package com.gt.family.tree;

import com.gt.family.family.Family;
import com.gt.family.family.Gender;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.RelationshipFinderFactory;
import com.gt.family.relationfinder.RelationshipsFinder;

import java.util.Optional;

public class FamilyTree {
    private Person familyHead;

    private FamilyTreeCreator tree = new FamilyTreeCreator("/family.txt");

    public FamilyTree() {
        familyHead = tree.familyTree();
    }

    public static Person findPerson(Person personOfInterest, String personName) {
        Optional<Family> family = personOfInterest.getFamily();
        if (!family.isPresent())
            return null;

        Family f = family.get();
        if (f.getWifeName().equals(personName))
            return f.getWife();

        if (f.getHusbandName().equals(personName))
            return f.getHusband();

        for (Person person : f.getChildren()) {
            if (person.getName().equals(personName))
                return person;

            Person person1 = findPerson(person, personName);
            if (person1 != null)
                return person1;
        }
        return null;
    }

    Person familyHead() {
        return familyHead;
    }

    public String addToFamilyTree(String mother, String childName, Gender gender) {
        Person child = new Person(childName, gender);
        return tree.addChild(familyHead, mother, child);
    }

    public String getRelationShip(String personName, String relationShip) {
        Person person = findPerson(familyHead, personName);
        if (person == null)
            return "PERSON_NOT_FOUND";

        RelationshipsFinder finder = RelationshipFinderFactory.findFor(relationShip);
        return finder != null ? finder.getRelationShip(person) : "UNSUPPORTED_RELATIONSHIP";
    }
}
