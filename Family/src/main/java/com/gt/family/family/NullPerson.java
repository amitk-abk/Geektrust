package com.gt.family.family;

import java.util.Optional;

import static com.gt.family.family.Gender.*;
import static com.gt.family.family.Gender.MALE;

public class NullPerson extends Person{

    private String name;
    private Gender gender;
    private Family family;
    private Person father;
    private Person mother;

    public NullPerson(String name, Gender gender) {
        super(name, gender);
        this.name = name;
        this.gender = gender;
    }

    public Optional<Family> getFamily() {
        return Optional.ofNullable(family);
    }

    public Person getFather() {
        return new NullPerson("Null Father", MALE);
    }

    public Person getMother() {
        return new NullPerson("Null Father", FEMALE);
    }
}
