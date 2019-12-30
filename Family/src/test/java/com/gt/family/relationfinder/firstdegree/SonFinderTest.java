package com.gt.family.relationfinder.firstdegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SonFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new SonFinder();

    @Test
    public void shouldReturnSonWhenExistsForPersonInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Jaya");
        assertEquals("Yodhan", finder.getRelationShip(person));
    }

    @Test
    public void shouldReturnNoneWhenPersonDoesNotHaveSonInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Lika");
        assertEquals("NONE", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Ish");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
