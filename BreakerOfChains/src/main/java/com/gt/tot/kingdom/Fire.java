package com.gt.tot.kingdom;

public class Fire extends Kingdom {

    private Fire() {
        emblem = "Dragon";
        rulerName = "Fire";
        name = "Fire";
    }

    private static final Fire FIRE_KINGDOM = new Fire();

    public static Fire kingdom() {
        return FIRE_KINGDOM;
    }

}
