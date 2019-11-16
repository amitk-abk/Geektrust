package com.gt.tot.kingdom;

public class Ice extends Kingdom {

    private Ice() {
        emblem = "Mammoth";
        rulerName = "Ice";
        name = "Ice";
    }

    private static final Ice ICE_KINGDOM = new Ice();

    public static Ice kingdom() {
        return ICE_KINGDOM;
    }

}
