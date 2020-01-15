package com.gt.traffic.mi.planet;

import com.gt.traffic.mi.weather.Weather;

import java.util.StringJoiner;

public class Orbit {

    private float lengthInMM;
    private int craters;
    private String name;
    private float trafficSpeed;
    private Suburb from;
    private Suburb to;

    private Orbit(As as) {
        this.lengthInMM = as.lengthInMM;
        this.craters = as.craters;
        this.name = as.name;
        this.trafficSpeed = as.trafficSpeed;
        this.from = as.source;
        this.to = as.destination;
    }

    public float getLengthInMM() {
        return lengthInMM;
    }

    public int getCraters() {
        return craters;
    }

    public int cratersInWeather(Weather weather) {
        return weather.modifiedCratersCount(this);
    }

    public void currentTrafficSpeed(float trafficSpeed) {
        this.trafficSpeed = trafficSpeed;
    }

    public float getTrafficSpeed() {
        return trafficSpeed;
    }

    public String getName() {
        return name;
    }

    public Suburb getFrom() {
        return from;
    }

    public Suburb getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orbit orbit = (Orbit) o;

        if (Float.compare(orbit.lengthInMM, lengthInMM) != 0) return false;
        if (craters != orbit.craters) return false;
        if (Float.compare(orbit.trafficSpeed, trafficSpeed) != 0) return false;
        if (!name.equals(orbit.name)) return false;
        if (from != orbit.from) return false;
        return to == orbit.to;
    }

    @Override
    public int hashCode() {
        int result = (lengthInMM != +0.0f ? Float.floatToIntBits(lengthInMM) : 0);
        result = 31 * result + craters;
        result = 31 * result + name.hashCode();
        result = 31 * result + (trafficSpeed != +0.0f ? Float.floatToIntBits(trafficSpeed) : 0);
        result = 31 * result + from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    public static class As {
        private float lengthInMM;
        private int craters;
        private String name;
        private float trafficSpeed;
        private Suburb source;
        private Suburb destination;

        public As(String name) {
            this.name = name;
        }

        public As length(float lengthInMM) {
            this.lengthInMM = lengthInMM;
            return this;
        }

        public As craters(int craters) {
            this.craters = craters;
            return this;
        }

        public As trafficSpeed(float trafficSpeed) {
            this.trafficSpeed = trafficSpeed;
            return this;
        }

        public As from(Suburb source) {
            this.source = source;
            return this;
        }

        public As to(Suburb destination) {
            this.destination = destination;
            return this;
        }

        public Orbit init() {
            return new Orbit(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Orbit.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
