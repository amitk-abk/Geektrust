package com.gt.tot.kingdom;

public class Land extends Kingdom {

    private Land() {
        emblem = "Panda";
        rulerName = "Land";
        name = "Land";
    }

    private static final Land LAND_KINGDOM = new Land();

    public static Land kingdom() {
        return LAND_KINGDOM;
    }

}
