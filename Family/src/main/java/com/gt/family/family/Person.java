package com.gt.family.family;

import java.util.List;
import java.util.Optional;

public class Person {

    private final String name;
    private final Gender gender;
    private Family family;
    private Person father;
    private Person mother;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Optional<Person> getWife() {
        if (family == null)
            return Optional.empty();
        return Optional.ofNullable(family.getWife());
    }

    public Optional<Person> getHusband() {
        if (family == null)
            return Optional.empty();
        return Optional.ofNullable(family.getHusband());
    }

    public void setFamily(Family family) {
        this.family = family;
    }


    public void setFather(Person father) {
        this.father = father;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public Person getMother() {
        return mother;
    }

    public Optional<Family> getFamily() {
        return Optional.ofNullable(this.family);
    }

    public List<Person> getChildren() {
        return family.getChildren();
    }
}
