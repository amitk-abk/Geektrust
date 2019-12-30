package com.gt.family.relationfinder.seconddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaternalUncleFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new PaternalUncleFinder();

    @Test
    public void shouldGetPaternalUncleForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Kriya");
        assertEquals("Asva", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Vritha");
        assertEquals("Ish Vich Aras", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneWhenPaternalUncleDoseNotExistInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Yodhan");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
