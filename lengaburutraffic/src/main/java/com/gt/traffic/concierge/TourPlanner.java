package com.gt.traffic.concierge;

import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Planet;
import com.gt.traffic.planet.Ruler;
import com.gt.traffic.planet.Suburb;
import com.gt.traffic.vehicle.Vehicle;
import com.gt.traffic.weather.Weather;

import java.util.*;
import java.util.stream.Collectors;

public class TourPlanner {

    private List<Orbit> available;
    private Map<String, Orbit> orbitsByName;
    private Ruler ruler;

    private TourPlanner(Planet planet) {
        this.ruler = planet.getRuler();
        this.available = planet.getOrbits();
        orbitsByName = new HashMap<>();
        for (Orbit orbit : planet.getOrbits()) {
            orbitsByName.put(orbit.getName(), orbit);
        }
    }

    public static TourPlanner planner(Planet planet) {
        return new TourPlanner(planet);
    }


    public String recommendationForJourney(Weather weather, Suburb origin, Suburb destination) {
        List<Vehicle> operableFleet = ruler.fleet().operableIn(weather);
        List<Orbit> availableOrbits = between(origin, destination);
        List<MeansPathTime> options = new ArrayList<>();
        for (Vehicle vehicle : operableFleet) {
            for (Orbit orbit : availableOrbits) {
                options.add(new MeansPathTime(orbit, vehicle, vehicle.hrsToTravelOrbitIn(orbit, weather)));
            }
        }

        MeansPathTime selected = selectOptimalFrom(options);
        return "Vehicle " + selected.getVehicle().vehicleName() + " on " + selected.getOrbit().getName();
    }

    private List<Orbit> between(Suburb origin, Suburb destination) {
        List<List<String>> routes = new RouteFinder(available)
                .findBetween(origin, destination);

        return routes.stream()
                .flatMap(List::stream)
                .filter(element -> retainOrbits(element, origin.suburbName(), destination.suburbName()))
                .map(orbit -> orbitsByName.get(orbit))
                .collect(Collectors.toList());
    }

    private boolean retainOrbits(String element, String source, String destination) {
        List<String> suburbs = Arrays.asList(source, destination);
        return !suburbs.contains(element);
    }

    private MeansPathTime selectOptimalFrom(List<MeansPathTime> options) {
        options.sort(Comparator.comparing(MeansPathTime::getHrsToTravel)
                .thenComparing(MeansPathTime::getPreference));
        return options.get(0);
    }

    private class MeansPathTime {
        private Orbit orbit;
        private Vehicle vehicle;
        private float hrsToTravel;
        private int preference;

        MeansPathTime(Orbit orbit, Vehicle vehicle, float hrsToTravel) {
            this.orbit = orbit;
            this.vehicle = vehicle;
            this.hrsToTravel = hrsToTravel;
            this.preference = assignPreferenceTo(vehicle);
        }

        private int assignPreferenceTo(Vehicle vehicle) {
            return ruler.preferenceOf(vehicle);
        }

        Orbit getOrbit() {
            return orbit;
        }

        Vehicle getVehicle() {
            return vehicle;
        }

        float getHrsToTravel() {
            return hrsToTravel;
        }

        int getPreference() {
            return preference;
        }
    }
}
