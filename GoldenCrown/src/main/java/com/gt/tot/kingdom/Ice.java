package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Ice extends Kingdom {

    private Ice() {
        emblem = "Mammoth";
        rulerName = "Ice King";
        name = "Ice";
        allies = new LinkedHashSet<>();
    }

    private static final Ice ICE_KINGDOM = new Ice();

    public static Ice kingdom() {
        return ICE_KINGDOM;
    }

}
