package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Air extends Kingdom {

    private static final Air AIR_KINGDOM = new Air();

    public Air() {
        rulerName = "Air King";
        emblem = "Owl";
        name = "Air";
        allies = new LinkedHashSet<>();
    }

    public static Air kingdom() {
        return AIR_KINGDOM;
    }
}
