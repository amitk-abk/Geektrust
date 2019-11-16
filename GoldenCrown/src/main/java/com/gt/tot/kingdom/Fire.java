package com.gt.tot.kingdom;

import java.util.LinkedHashSet;

public class Fire extends Kingdom {

    private Fire() {
        emblem = "Dragon";
        rulerName = "Fire King";
        name = "Fire";
        allies = new LinkedHashSet<>();
    }

    private static final Fire FIRE_KINGDOM = new Fire();

    public static Fire kingdom() {
        return FIRE_KINGDOM;
    }

}
