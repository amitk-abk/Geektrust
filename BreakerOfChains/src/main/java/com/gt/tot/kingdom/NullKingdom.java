package com.gt.tot.kingdom;

public class NullKingdom extends Kingdom {

    public NullKingdom() {
        emblem = "None";
        rulerName = "None";
        name = "None";
    }

    private static final NullKingdom NULL_KINGDOM = new NullKingdom();

    public static Kingdom kingdom() {
        return NULL_KINGDOM;
    }

    @Override
    public String alliesNames() {
        return name;
    }
}
