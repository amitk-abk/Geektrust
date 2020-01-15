package com.gt.traffic.mi.vehicle;

import com.gt.traffic.mi.weather.Weather;

import java.util.List;
import java.util.stream.Collectors;

public class Fleet {

    private final List<Vehicle> fleet;

    public Fleet(List<Vehicle> fleet) {
        this.fleet = fleet;
    }

    public List<Vehicle> operableIn(Weather weather) {
        return fleet.stream()
                .filter(vehicle -> vehicle.canTravelIn(weather))
                .collect(Collectors.toList());
    }
}
