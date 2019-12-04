package com.gt.traffic.vehicle;

import com.gt.traffic.weather.Weather;

import static com.gt.traffic.weather.Weather.WINDY;

public class Tuktuk extends Vehicle {

    public Tuktuk(String name, float hourlySpeed, float minutesToCrossCrater) {
        this.name = name;
        this.hourlySpeed = hourlySpeed;
        this.minutesToCrossCrater = minutesToCrossCrater;
    }


    @Override
    public boolean canTravelIn(Weather weather) {
        return !weather.equals(WINDY);
    }
}
