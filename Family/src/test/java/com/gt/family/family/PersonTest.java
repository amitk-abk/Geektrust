package com.gt.family.family;

import com.gt.family.family.Family;
import com.gt.family.family.Person;
import org.junit.Test;

import java.util.Arrays;

import static com.gt.family.family.Gender.FEMALE;
import static com.gt.family.family.Gender.MALE;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void shouldProvidePersonalDetails() throws Exception {
        Person person = new Person("Husband", MALE);
        assertEquals("Husband", person.getName());
        assertEquals(MALE, person.getGender());
    }

    // Family once created with husband and wife, is final. Change of spouse is not possible
    @Test
    public void shouldProvideSpouse() throws Exception {
        Person husband = new Person("Husband", MALE);
        Person wife = new Person("Wife", FEMALE);
        new Family.Of(husband).wife(wife).family();

        assertEquals("Wife", husband.getWife().get().getName());
    }

    @Test
    public void shouldBeAbleToProvideParentsAndKidsDetails() throws Exception {
        Person husband = new Person("Husband", MALE);
        Person wife = new Person("Wife", FEMALE);
        Family family = new Family.Of(husband).wife(wife).family();

        Person kid1 = new Person("Kid1", MALE);
        Person kid2 = new Person("Kid2", FEMALE);
        family.addChild(kid1);
        family.addChild(kid2);

        assertEquals(husband, kid1.getFather());
        assertEquals(wife, kid1.getMother());
        assertEquals(Arrays.asList(kid1,kid2), husband.getChildren());
        assertEquals(Arrays.asList(kid1,kid2), wife.getChildren());
    }
}
