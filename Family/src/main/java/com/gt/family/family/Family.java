package com.gt.family.family;


import java.util.ArrayList;
import java.util.List;

public class Family {

    private final Person husband;
    private final Person wife;
    private final List<Person> children;

    private Family(Of of) {
        this.husband = of.husband;
        this.wife = of.wife;
        children = new ArrayList<>();
    }

    public Person getHusband() {
        return this.husband;
    }

    public Person getWife() {
        return this.wife;
    }

    public void addChild(Person child) {
        child.setFather(husband);
        child.setMother(wife);
        this.children.add(child);
    }

    public List<Person> getChildren() {
        return this.children;
    }

    public String getWifeName() {
        return this.wife.getName();
    }

    public String getHusbandName() {
        return this.husband.getName();
    }

    public static class Of {
        private Person husband;
        private Person wife;

        public Of(Person husband) {
            this.husband = husband;
        }

        public Of wife(Person wife) {
            this.wife = wife;
            return this;
        }

        public Family family() {
            Family family = new Family(this);
            this.husband.setFamily(family);
            this.wife.setFamily(family);
            return family;
        }
    }
}
