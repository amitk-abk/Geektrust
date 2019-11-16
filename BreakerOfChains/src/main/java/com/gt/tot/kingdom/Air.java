package com.gt.tot.kingdom;

public class Air extends Kingdom {

    private static final Air AIR_KINGDOM = new Air();

    public Air() {
        rulerName = "Air";
        emblem = "Owl";
        name = "Air";
    }

    public static Air kingdom() {
        return AIR_KINGDOM;
    }
}
