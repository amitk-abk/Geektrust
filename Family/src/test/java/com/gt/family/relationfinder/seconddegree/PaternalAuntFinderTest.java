package com.gt.family.relationfinder.seconddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaternalAuntFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new PaternalAuntFinder();

    @Test
    public void shouldGetPaternalAuntForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Ahit");
        assertEquals("Satya", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneAsPaternalAuntWhenNotFoundInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Laki");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
