package com.gt.traffic.vehicle;

import com.gt.traffic.weather.Weather;

public class SuperCar extends Vehicle {

    public SuperCar(String name, float hourlySpeed, float minutesToCrossCrater) {
        this.name = name;
        this.hourlySpeed = hourlySpeed;
        this.minutesToCrossCrater = minutesToCrossCrater;
    }


    @Override
    public boolean canTravelIn(Weather weather) {
        return true;
    }
}
