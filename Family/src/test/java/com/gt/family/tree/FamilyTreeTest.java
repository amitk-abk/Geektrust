package com.gt.family.tree;

import com.gt.family.family.Person;
import org.junit.Test;

import static com.gt.family.family.Gender.FEMALE;
import static com.gt.family.family.Gender.MALE;
import static org.junit.Assert.assertEquals;

public class FamilyTreeTest {

    private final FamilyTree tree = new FamilyTree();

    @Test
    public void shouldProvideKingShanAsFamilyHead() throws Exception {
        Person head = tree.familyHead();
        assertEquals("King Shan", head.getName());
    }

    @Test
    public void shouldProvideFamilyHeadLady() throws Exception {
        Person head = tree.familyHead();
        assertEquals("Queen Anga", head.getWife().get().getName());
    }

    @Test
    public void shouldAddChildToTreeFromMothersSide() throws Exception {
        assertEquals("CHILD_ADDITION_SUCCEEDED", tree.addToFamilyTree("Chitra", "Aria", FEMALE));
        Person child = FamilyTree.findPerson(tree.familyHead(), "Aria");
        assertEquals("Chitra", child.getMother().getName());
    }

    @Test
    public void shouldReturnMaternalAuntIfExists() throws Exception {
        tree.addToFamilyTree("Chitra", "Aria", FEMALE);
        assertEquals("Aria", tree.getRelationShip("Lavnya", "Maternal-Aunt"));

        tree.addToFamilyTree("Amba", "Chibha", FEMALE);
        assertEquals("Tritha Chibha", tree.getRelationShip("Yodhan", "Maternal-Aunt"));
    }

    @Test
    public void shouldReturnMaternalUncleIfExists() throws Exception {
        assertEquals("Vritha", tree.getRelationShip("Yodhan", "Maternal-Uncle"));
        assertEquals("Chit Ish Vich Aras", tree.getRelationShip("Vyas", "Maternal-Uncle"));
    }

    @Test
    public void shouldReturnSiblings() throws Exception {
        tree.addToFamilyTree("Chitra", "Aria", FEMALE);
        assertEquals("Jnki Ahit", tree.getRelationShip("Aria", "Siblings"));
    }

    @Test
    public void shouldReturnNoneWhenNoSiblingsFoundForPersonWhoExistsInFamilyTree() throws Exception {
        assertEquals("NONE", tree.getRelationShip("Vasa", "Siblings"));
    }

    @Test
    public void shouldReturnPaternalAuntWhenExistsInFamilyTree() throws Exception {
        assertEquals("Atya", tree.getRelationShip("Vasa", "Paternal-Aunt"));

        tree.addToFamilyTree("Queen Anga", "Shaga", FEMALE);
        assertEquals("Satya Shaga", tree.getRelationShip("Dritha", "Paternal-Aunt"));
    }

    @Test
    public void shouldReturnPaternalUncleWhenExistsInFamilyTree() throws Exception {
        assertEquals("Asva", tree.getRelationShip("Krithi", "Paternal-Uncle"));
        assertEquals("Chit Ish Aras", tree.getRelationShip("Vila", "Paternal-Uncle"));
    }

    @Test
    public void shouldReturnPaternalUncleWhenExistsInFamilyTreeForNewChild() throws Exception {
        tree.addToFamilyTree("Satvy", "Assavi", FEMALE);
        assertEquals("Vyas", tree.getRelationShip("Assavi", "Paternal-Uncle"));
    }

    @Test
    public void shouldReturnSisterInLawWhenExists() throws Exception {
        assertEquals("Satvy Krpi", tree.getRelationShip("Atya", "Sister-In-Law"));
        assertEquals("Atya", tree.getRelationShip("Satvy", "Sister-In-Law"));
        assertEquals("Tritha", tree.getRelationShip("Jaya", "Sister-In-Law"));
        assertEquals("Satvy", tree.getRelationShip("Vyas", "Sister-In-Law"));
        assertEquals("Amba Lika Chitra", tree.getRelationShip("Satya", "Sister-In-Law"));
        assertEquals("Satya", tree.getRelationShip("Lika", "Sister-In-Law"));

        tree.addToFamilyTree("Chitra", "Aria", FEMALE);
        assertEquals("Aria", tree.getRelationShip("Arit", "Sister-In-Law"));
    }

    @Test
    public void shouldReturnNoneWhenSisterInLawDoesNotExists() throws Exception {
        assertEquals("NONE", tree.getRelationShip("Ahit", "Sister-In-Law"));
    }

    @Test
    public void shouldReturnBrotherInLawWhenExists() throws Exception {
        assertEquals("Chit Ish Aras", tree.getRelationShip("Lika", "Brother-In-Law"));
        assertEquals("Vritha", tree.getRelationShip("Jaya", "Brother-In-Law"));
        assertEquals("Jaya", tree.getRelationShip("Tritha", "Brother-In-Law"));
        assertEquals("Jaya", tree.getRelationShip("Vritha", "Brother-In-Law"));
        assertEquals("Arit", tree.getRelationShip("Ahit", "Brother-In-Law"));

        tree.addToFamilyTree("Satya", "Nayan", MALE);
        assertEquals("Asva Nayan", tree.getRelationShip("Krpi", "Brother-In-Law"));
    }

    @Test
    public void shouldReturnSonIfPresent() throws Exception {
        assertEquals("Ahit", tree.getRelationShip("Aras", "Son"));
        assertEquals("Ahit", tree.getRelationShip("Chitra", "Son"));
        assertEquals("Chit Ish Vich Aras", tree.getRelationShip("King Shan", "Son"));

        tree.addToFamilyTree("Satya", "Nayan", MALE);
        assertEquals("Asva Vyas Nayan", tree.getRelationShip("Vyan", "Son"));
    }

    @Test
    public void shouldReturnNoneIsPersonDoesNotHaveSon() throws Exception {
        assertEquals("NONE", tree.getRelationShip("Vritha", "Son"));
    }

    @Test
    public void shouldReturnDaughterIfPresent() throws Exception {
        assertEquals("Jnki", tree.getRelationShip("Aras", "Daughter"));
        assertEquals("Vila Chika", tree.getRelationShip("Vich", "Daughter"));

        tree.addToFamilyTree("Chitra", "Aria", FEMALE);
        assertEquals("Jnki Aria", tree.getRelationShip("Aras", "Daughter"));
    }

    @Test
    public void shouldReportNotFoundWhenAddedChildToMotherWhoDoesNotExistInFamilyTree() throws Exception {
        assertEquals("PERSON_NOT_FOUND", tree.addToFamilyTree("Pjali", "Srutak", MALE));
    }


    @Test
    public void shouldReportNoneForSiblingsAuntUncleRelationsOfKingShen() throws Exception {
        assertEquals("NONE", tree.getRelationShip("King Shan", "Maternal-Aunt"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Siblings"));
    }

    @Test
    public void shouldNotAllowedToAddChildThroughFather() throws Exception {
        assertEquals("CHILD_ADDITION_FAILED", tree.addToFamilyTree("Asva", "Vani", FEMALE));
    }

    @Test
    public void shouldReportUnsupportedRelationShip() throws Exception {
        assertEquals("UNSUPPORTED_RELATIONSHIP", tree.getRelationShip("Asva", "Grand-Father"));
    }

    @Test
    public void shouldReportNotFoundWhenSoughtRelationshipWithNonExistingPersonInFamilyTree() throws Exception {
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Son"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Daughter"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Siblings"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Maternal-Aunt"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Maternal-Uncle"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Paternal-Aunt"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Paternal-Uncle"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Brother-In-Law"));
        assertEquals("PERSON_NOT_FOUND", tree.getRelationShip("Pjali", "Sister-In-Law"));
    }

    @Test
    public void shouldReportNoneForNonExistingRelationsOfFamilyHeadCouple() throws Exception {
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Siblings"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Maternal-Aunt"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Maternal-Uncle"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Paternal-Aunt"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Paternal-Uncle"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Brother-In-Law"));
        assertEquals("NONE", tree.getRelationShip("Queen Anga", "Sister-In-Law"));

        assertEquals("NONE", tree.getRelationShip("King Shan", "Siblings"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Maternal-Aunt"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Maternal-Uncle"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Paternal-Aunt"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Paternal-Uncle"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Brother-In-Law"));
        assertEquals("NONE", tree.getRelationShip("King Shan", "Sister-In-Law"));
    }
}
