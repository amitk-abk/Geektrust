package com.gt.family.relationfinder;

import com.gt.family.tree.FamilyTreeCreator;
import com.gt.family.family.Person;

public class FamilyTreeProvider {

    public static Person getFamilyTree() {
        FamilyTreeCreator creator = new FamilyTreeCreator("/family.txt");
        return creator.familyTree();
    }
}
