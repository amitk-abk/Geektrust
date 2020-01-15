package com.gt.traffic.mi.planet;

import java.util.List;


public class Planet {
    private String name;
    private List<Suburb> suburbs;
    private List<Orbit> orbits;
    private Ruler ruler;

    Planet(Of of) {
        this.name = of.name;
        this.suburbs = of.suburbs;
        this.orbits = of.orbits;
        this.ruler = of.ruler;
    }

    public String getName() {
        return name;
    }

    public List<Suburb> getSuburbs() {
        return suburbs;
    }

    public List<Orbit> getOrbits() {
        return orbits;
    }

    public Ruler getRuler() {
        return ruler;
    }

    public static class Of {
        private String name;
        private List<Suburb> suburbs;
        private List<Orbit> orbits;
        private Ruler ruler;

        public Of(String name) {
            this.name = name;
        }

        public Of ruler(Ruler ruler) {
            this.ruler = ruler;
            return this;
        }

        public Of orbits(List<Orbit> orbits) {
            this.orbits = orbits;
            return this;
        }

        public Of suburbs(List<Suburb> suburbs) {
            this.suburbs = suburbs;
            return this;
        }

        public Planet init() {
            return new Planet(this);
        }
    }

}
