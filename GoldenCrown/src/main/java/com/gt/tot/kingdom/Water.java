package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Water extends Kingdom {

    private Water() {
        emblem = "Octopus";
        rulerName = "Water King";
        name = "Water";
        allies = new LinkedHashSet<>();
    }

    private static final Water WATER_KINGDOM = new Water();

    public static Water kingdom() {
        return WATER_KINGDOM;
    }

}
