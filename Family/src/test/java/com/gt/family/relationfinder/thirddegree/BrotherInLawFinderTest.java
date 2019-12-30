package com.gt.family.relationfinder.thirddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BrotherInLawFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new BrotherInLawFinder();

    @Test
    public void shouldGetBILForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Satvy");
        assertEquals("Vyas", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Jaya");
        assertEquals("Vritha", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Arit");
        assertEquals("Ahit", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Lika");
        assertEquals("Chit Ish Aras", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneWhenBILDoseNotExistInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Chika");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
