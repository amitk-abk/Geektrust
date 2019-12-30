package com.gt.family.relationfinder.firstdegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaughterFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new DaughterFinder();

    @Test
    public void shouldFindDaughterForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Amba");
        assertEquals("Dritha Tritha", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneWhenPersonDoesNotHaveDaughterInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Ish");
        assertEquals("NONE", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Dritha");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
