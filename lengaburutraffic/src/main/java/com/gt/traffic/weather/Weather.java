package com.gt.traffic.weather;

import com.gt.traffic.planet.Orbit;

public enum Weather {

    RAINY("rainy") {
        @Override
        public int modifiedCratersCount(Orbit orbit) {
            return orbit.getCraters() + ((orbit.getCraters() * 20) / 100);
        }
    }, WINDY("windy") {
        @Override
        public int modifiedCratersCount(Orbit orbit) {
            return orbit.getCraters();
        }
    }, SUNNY("sunny") {
        @Override
        public int modifiedCratersCount(Orbit orbit) {
            return orbit.getCraters() - ((orbit.getCraters() * 10) / 100);
        }
    };

    private String weatherCondition;

    Weather(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String condition() {
        return weatherCondition;
    }

    public abstract int modifiedCratersCount(Orbit orbit);
}
