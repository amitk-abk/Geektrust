package com.gt.family.relationfinder.seconddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaternalUncleFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new MaternalUncleFinder();

    @Test
    public void shouldGetMaternalUncleForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Yodhan");
        assertEquals("Vritha", finder.getRelationShip(person));

        person = FamilyTree.findPerson(headPerson, "Atya");
        assertEquals("Chit Ish Vich Aras", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneWhenMaternalUncleDoseNotExistInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Vila");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
