package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Land extends Kingdom {

    private Land() {
        emblem = "Panda";
        rulerName = "Land King";
        name = "Land";
        allies = new LinkedHashSet<>();
    }

    private static final Land LAND_KINGDOM = new Land();

    public static Land kingdom() {
        return LAND_KINGDOM;
    }

}
