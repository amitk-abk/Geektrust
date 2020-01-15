package com.gt.traffic.mi.vehicle;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.weather.Weather;

public abstract class Vehicle {
    String name;
    float hourlySpeed;
    float minutesToCrossCrater;

    float hrsToTravelMegaMiles(float distance) {
        return distance / hourlySpeed;
    }

    public String vehicleName() {
        return name;
    }

    protected abstract boolean canTravelIn(Weather weather);

    public float hrsToTravelOrbitIn(Orbit orbit, Weather weather) {
        if (!canTravelIn(weather))
            throw new RuntimeException("Can not travel in " + weather.condition() + " weather!");

        float vehicleSpeed = hourlySpeed;
        hourlySpeed = adjustedPerTrafficSpeedOf(orbit);

        float hrsToTravelOrbit = hrsToCrossCratersIn(orbit, weather)
                + hrsToTravelMegaMiles(orbit.getLengthInMM());

        hourlySpeed = vehicleSpeed;
        return hrsToTravelOrbit;
    }

    private float adjustedPerTrafficSpeedOf(Orbit orbit) {
        return hourlySpeed > orbit.getTrafficSpeed() ? orbit.getTrafficSpeed() : hourlySpeed;
    }

    private float hrsToCrossCratersIn(Orbit orbit, Weather weather) {
        return (orbit.cratersInWeather(weather) * this.minutesToCrossCrater) / 60;
    }
}
