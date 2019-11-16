package com.gt.tot.kingdom;

public class Water extends Kingdom {

    private Water() {
        emblem = "Octopus";
        rulerName = "Water";
        name = "Water";
    }

    private static final Water WATER_KINGDOM = new Water();

    public static Water kingdom() {
        return WATER_KINGDOM;
    }

}
