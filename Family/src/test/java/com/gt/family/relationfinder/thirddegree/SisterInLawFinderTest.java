package com.gt.family.relationfinder.thirddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SisterInLawFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new SisterInLawFinder();

    @Test
    public void shouldGetSILForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Atya");
        assertEquals("Satvy Krpi", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Jaya");
        assertEquals("Tritha", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Satya");
        assertEquals("Amba Lika Chitra", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneWhenSILDoseNotExistInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Ahit");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
