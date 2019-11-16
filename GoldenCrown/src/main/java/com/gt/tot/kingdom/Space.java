package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Space extends Kingdom {

    private Space() {
        emblem = "Gorilla";
        rulerName = "King Shan";
        name = "Space";
        allies = new LinkedHashSet<>();
    }

    private static final Space SPACE_KINGDOM = new Space();

    public static Space kingdom() {
        return SPACE_KINGDOM;
    }

}
