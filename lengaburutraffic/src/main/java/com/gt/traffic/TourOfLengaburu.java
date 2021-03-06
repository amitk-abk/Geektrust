package com.gt.traffic;

import com.gt.traffic.concierge.TourPlanner;
import com.gt.traffic.io.Initializer;
import com.gt.traffic.io.InputParser;
import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Planet;
import com.gt.traffic.planet.Ruler;
import com.gt.traffic.vehicle.Fleet;
import com.gt.traffic.weather.Weather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;

class TourOfLengaburu {

    private final InputParser parse = new InputParser();
    private final Initializer initializer;

    public TourOfLengaburu(String initializationFile) {
        initializer = new Initializer(initializationFile);
    }

    void doTour(String inputFile) {
        Planet Lengaburu = planetOfLengaburu();

        String input = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(inputFile)))) {
            do {
                input = reader.readLine();

                if (input.equals("q"))
                    break;

                String weatherInput = weatherInputFrom(input);

                List<String> orbitInput = new ArrayList<>();
                do {
                    if (input.equals("q")) {
                        break;
                    }
                    if (input.length() > 0 && input.contains("Orbit")) {
                        orbitInput.add(input);
                    }

                    input = reader.readLine();
                } while (input.contains("Orbit"));

                if (weatherInput.length() > 0 && !orbitInput.isEmpty()) {
                    Weather weatherCondition = parse.weatherFrom(weatherInput);
                    updateOrbitsWithCurrentTrafficSpeed(Lengaburu, orbitInput);

                    TourPlanner tourPlanner = TourPlanner.planner(Lengaburu);
                    System.out.println(tourPlanner.recommendationForJourney(weatherCondition, SILKDORB, HALLITHARAM));
                }

            } while (!input.equals("q"));
        } catch (IOException e) {
            System.out.println("Opps, something went wrong regarding input file.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String weatherInputFrom(String input) {
        if (input.contains("Weather"))
            return input;
        return "";
    }

    private void updateOrbitsWithCurrentTrafficSpeed(Planet lengaburu, List<String> orbitInput) {
        for (String input : orbitInput) {
            Orbit orbit = parse.orbitFrom(input, lengaburu);
            orbit.currentTrafficSpeed(Float.valueOf(parse.speedFrom(input)));
        }
    }

    private Planet planetOfLengaburu() {
        return new Planet.Of("Lengaburu")
                .ruler(rulerOfLengaburu())
                .orbits(initializer.orbitsInLengaburu())
                .init();
    }

    private Ruler rulerOfLengaburu() {
        Ruler ruler = new Ruler("King Shan");
        ruler.hasFleet(new Fleet(initializer.fleet()));
        return ruler;
    }

}
