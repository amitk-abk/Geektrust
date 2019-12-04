package com.gt.traffic.planet;

public enum Suburb {
    SILKDORB("Silk Dorb"), HALLITHARAM("Hallitharam");

    private final String name;

    Suburb(String name) {
        this.name = name;
    }

    public String suburbName() {
        return this.name;
    }
}
