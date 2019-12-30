package com.gt.family.relationfinder.seconddegree;

import com.gt.family.tree.FamilyTree;
import com.gt.family.family.Person;
import com.gt.family.relationfinder.FamilyTreeProvider;
import com.gt.family.relationfinder.RelationshipsFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaternalAuntFinderTest {

    private final Person headPerson = FamilyTreeProvider.getFamilyTree();
    private RelationshipsFinder finder = new MaternalAuntFinder();

    @Test
    public void shouldGetMaternalAuntForPersonIfExistsInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Yodhan");
        assertEquals("Tritha", finder.getRelationShip(person));
    }

    @Test
    public void shouldReportNoneAsMaternalAuntWhenNotFoundInFamilyTree() throws Exception {
        Person person = FamilyTree.findPerson(headPerson, "Vasa");
        assertEquals("NONE", finder.getRelationShip(person));
    }
}
