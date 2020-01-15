package com.gt.traffic.mi.vehicle;

import com.gt.traffic.mi.weather.Weather;

import static com.gt.traffic.mi.weather.Weather.WINDY;

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
