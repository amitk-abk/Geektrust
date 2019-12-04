package com.gt.traffic.vehicle;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.gt.traffic.weather.Weather.*;
import static org.junit.Assert.assertEquals;

public class FleetTest {

    @Test
    public void shouldFilterFleetForGivenWeather() throws Exception {
        Fleet fleet = new Fleet(fleetOfKingShan());
        List<Vehicle> operableFleet = fleet.operableIn(RAINY);
        assertEquals(2, operableFleet.size());
        assertEquals(Arrays.asList("Car", "Tuktuk"), vehiclesInFleet(operableFleet));

        operableFleet = fleet.operableIn(WINDY);
        assertEquals(2, operableFleet.size());
        assertEquals(Arrays.asList("Bike", "Car"), vehiclesInFleet(operableFleet));

        operableFleet = fleet.operableIn(SUNNY);
        assertEquals(3, operableFleet.size());
        assertEquals(Arrays.asList("Bike", "Car", "Tuktuk"), vehiclesInFleet(operableFleet));
    }

    private List<Vehicle> fleetOfKingShan() {
        return Arrays.asList(new Bike("Bike",10, 2),
                new Tuktuk("Tuktuk",12, 1),
                new SuperCar("Car",20, 3));
    }

    private List<String> vehiclesInFleet(List<Vehicle> workableFleet) {
        workableFleet.sort(Comparator.comparing(Vehicle::vehicleName));
        return workableFleet.stream()
                .map(Vehicle::vehicleName)
                .collect(Collectors.toList());
    }

}
