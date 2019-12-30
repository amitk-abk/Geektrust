package com.gt.family.relationfinder;

import com.gt.family.relationfinder.firstdegree.DaughterFinder;
import com.gt.family.relationfinder.firstdegree.SiblingsFinder;
import com.gt.family.relationfinder.firstdegree.SonFinder;
import com.gt.family.relationfinder.seconddegree.MaternalAuntFinder;
import com.gt.family.relationfinder.seconddegree.MaternalUncleFinder;
import com.gt.family.relationfinder.seconddegree.PaternalAuntFinder;
import com.gt.family.relationfinder.seconddegree.PaternalUncleFinder;
import com.gt.family.relationfinder.thirddegree.BrotherInLawFinder;
import com.gt.family.relationfinder.thirddegree.SisterInLawFinder;

public class RelationshipFinderFactory {

    public static RelationshipsFinder findFor(String relationShip) {
        switch (relationShip) {
            case "Son":
                return new SonFinder();
            case "Daughter":
                return new DaughterFinder();
            case "Siblings":
                return new SiblingsFinder();

            case "Maternal-Aunt":
                return new MaternalAuntFinder();
            case "Maternal-Uncle":
                return new MaternalUncleFinder();
            case "Paternal-Aunt":
                return new PaternalAuntFinder();
            case "Paternal-Uncle":
                return new PaternalUncleFinder();

            case "Sister-In-Law":
                return new SisterInLawFinder();
            case "Brother-In-Law":
                return new BrotherInLawFinder();
        }
        return null;
    }
}
