package com.gt.traffic.mi.planet;

public enum Suburb {
    SILKDORB("Silk Dorb"), HALLITHARAM("Hallitharam"), RKPURAM("R K Puram");

    private final String name;

    Suburb(String name) {
        this.name = name;
    }

    public String suburbName() {
        return this.name;
    }

    public Suburb fromName(String name) {
        return Suburb.valueOf(Suburb.class, name);
    }
}
