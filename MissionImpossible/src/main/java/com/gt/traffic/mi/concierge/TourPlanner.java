package com.gt.traffic.mi.concierge;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.planet.Planet;
import com.gt.traffic.mi.planet.Ruler;
import com.gt.traffic.mi.planet.Suburb;
import com.gt.traffic.mi.vehicle.Vehicle;
import com.gt.traffic.mi.weather.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TourPlanner {

    private List<Orbit> available;
    private Map<String, Orbit> orbitsByName;
    private Ruler ruler;
    private List<List<MeansPathTime>> options;

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


    public String recommendationForJourney(Weather weather, Suburb origin, Suburb... destinations) {
        List<Vehicle> operableFleet = fleetOperableIn(weather);
        List<List<String>> tourOptions = between(origin, destinations);
        List<List<MeansPathTime>> options = tourOptionsFor(weather, operableFleet, tourOptions);
        List<MeansPathTime> optimalOption = selectOptimalFrom(options);
        return String.join(" and ", recommendationFor(optimalOption));
    }

    private List<List<MeansPathTime>> tourOptionsFor(Weather weather, List<Vehicle> operableFleet, List<List<String>> tourOptions) {
        List<List<MeansPathTime>> options = new ArrayList<>();
        for (Vehicle vehicle : operableFleet) {
            for (List<String> orbitsInfo : tourOptions) {
                List<Orbit> orbits = orbitsFrom(orbitsInfo);

                List<MeansPathTime> meansPathTimes = new ArrayList<>();
                for (Orbit orbit : orbits) {
                    String start = startFrom(orbit, orbitsInfo);
                    String end = endFrom(orbit, orbitsInfo);
                    meansPathTimes.add(new MeansPathTime(orbit, vehicle, vehicle.hrsToTravelOrbitIn(orbit, weather), start, end));
                }
                options.add(meansPathTimes);
            }
        }
        return options;
    }

    private List<Orbit> orbitsFrom(List<String> orbitsInfo) {
        return orbitsInfo.stream()
                .filter(s -> s.startsWith("Orbit"))
                .map(orbit -> orbitsByName.get(orbit))
                .collect(Collectors.toList());
    }

    private List<String> recommendationFor(List<MeansPathTime> selecteds) {
        String template1 = "Vehicle %1$s to %2$s via %3$s";
        String template2 = "%1$s via %2$s";
        List<String> details = new ArrayList<>();

        String vehicleName = "";
        for (MeansPathTime selected : selecteds) {
            if (!vehicleName.equals(selected.getVehicle().vehicleName())) {
                details.add(String.format(template1,
                        selected.getVehicle().vehicleName(), selected.getDestination(), selected.getOrbit().getName()));
                vehicleName = selected.getVehicle().vehicleName();
            } else {
                details.add(String.format(template2, selected.getDestination(), selected.getOrbit().getName()));
            }
        }
        return details;
    }

    private List<Vehicle> fleetOperableIn(Weather weather) {
        return ruler.fleet().operableIn(weather);
    }

    private String endFrom(Orbit orbit, List<String> orbitsInfo) {
        int found = orbitsInfo.indexOf(orbit.getName());
        return orbitsInfo.get(found + 1);
    }

    private String startFrom(Orbit orbit, List<String> orbitsInfo) {
        int found = orbitsInfo.indexOf(orbit.getName());
        return orbitsInfo.get(found - 1);
    }

    private List<List<String>> between(Suburb origin, Suburb... destinations) {
        List<List<String>> routes = new RouteFinder(available)
                .findBetween(origin, destinations);

        return routes.stream()
                .filter(route -> containsSuburbs(route, origin, destinations))
                .collect(Collectors.toList());
    }

    private boolean containsSuburbs(List<String> route, Suburb origin, Suburb... destinations) {
        List<String> requiredSuburbs = new ArrayList<>();
        requiredSuburbs.add(origin.suburbName());
        for (Suburb suburb : destinations)
            requiredSuburbs.add(suburb.suburbName());

        List<String> suburbsOnRoute = route.stream()
                .filter(r -> !r.startsWith("Orbit"))
                .collect(Collectors.toList());

        requiredSuburbs.sort(String::compareTo);
        suburbsOnRoute.sort(String::compareTo);
        return requiredSuburbs.equals(suburbsOnRoute);
    }


    private List<MeansPathTime> selectOptimalFrom(List<List<MeansPathTime>> options) {
        options.sort((o1, o2) -> {
            float one = getTotalJourneyHours(o1);
            float two = getTotalJourneyHours(o2);
            return Float.compare(one, two);
        });
        return options.get(0);
    }


    private float getTotalJourneyHours(List<MeansPathTime> pathTimes) {
        return pathTimes
                .stream()
                .map(MeansPathTime::getHrsToTravel)
                .reduce((aFloat1, aFloat2) -> aFloat1 + aFloat2)
                .get();
    }

    private class MeansPathTime {
        private Orbit orbit;
        private Vehicle vehicle;
        private float hrsToTravel;
        private int preference;
        private String origin;
        private String destination;

        MeansPathTime(Orbit orbit, Vehicle vehicle, float hrsToTravel, String origin, String destination) {
            this.orbit = orbit;
            this.vehicle = vehicle;
            this.hrsToTravel = hrsToTravel;
            this.preference = assignPreferenceTo(vehicle);
            this.origin = origin;
            this.destination = destination;
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

        public String getOrigin() {
            return origin;
        }

        String getDestination() {
            return destination;
        }
    }
}
