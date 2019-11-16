package com.gt.tot.kingdom;

public class Space extends Kingdom {

    private Space() {
        emblem = "Gorilla";
        rulerName = "King Shan (Space)";
        name = "Space";
    }

    private static final Space SPACE_KINGDOM = new Space();

    public static Space kingdom() {
        return SPACE_KINGDOM;
    }

}
