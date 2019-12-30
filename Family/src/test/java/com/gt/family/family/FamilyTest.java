package com.gt.family.family;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import org.junit.Test;

import java.util.Arrays;

import static com.gt.family.family.Gender.FEMALE;
import static com.gt.family.family.Gender.MALE;
import static org.junit.Assert.assertEquals;


public class FamilyTest {

    @Test
    public void shouldProvideHusbandWifeOfFamily() throws Exception {
        Person man = new Person("Man", MALE);
        Person wife = new Person("Wife", FEMALE);
        Family family = new Family.Of(man).wife(wife).family();
        assertEquals(man, family.getHusband());
        assertEquals(wife, family.getWife());
    }

    @Test
    public void shouldBeAbleToAddChildrenToFamily() throws Exception {
        Person man = new Person("Man", MALE);
        Person wife = new Person("Wife", FEMALE);
        Family family = new Family.Of(man).wife(wife).family();

        Person kid1 = new Person("Kid1", MALE);
        Person kid2 = new Person("Kid2", FEMALE);
        family.addChild(kid1);
        family.addChild(kid2);

        assertEquals(2, man.getChildren().size());
        assertEquals(Arrays.asList(kid1, kid2), man.getChildren());
    }
}
