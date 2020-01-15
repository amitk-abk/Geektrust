package com.gt.traffic.mi.io;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.planet.Planet;
import com.gt.traffic.mi.weather.Weather;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class InputParser {

    public Weather weatherFrom(String weatherInput) {
        String now = getWeatherFrom(weatherInput);
        Optional<Weather> weather = Stream.of(Weather.values())
                .filter(weather1 -> weather1.condition().equalsIgnoreCase(now))
                .findFirst();
        return weather.orElseThrow(() -> new RuntimeException("Weather could not be determined from input"));
    }

    private String getWeatherFrom(String weatherInput) {
        String regEx = "\\s(\\w+)$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(weatherInput);
        if (m.find()) {
            return m.group(1);
        }
        throw new RuntimeException("Weather could not be determined from input");
    }

    public Orbit orbitFrom(String input, Planet lengaburu) {
        String orbit1 = orbitFrom(input);
        Optional<Orbit> orbit = lengaburu.getOrbits()
                .stream()
                .filter(orbt -> orbt.getName().equals(orbit1))
                .findFirst();
        return orbit.orElseThrow(() -> new RuntimeException("Orbit could not be determined from input"));
    }


    private String orbitFrom(String input) {
        String regEx = "(Orbit\\d)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            String group = m.group(1);
            return group;
        }
        throw new RuntimeException("Orbit could not be determined from input");
    }

    public String speedFrom(String input) {
        String regEx = "(\\d+)\\s(megamiles/hour)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            return m.group(1);
        }
        throw new RuntimeException("Orbit traffic could not be determined from input");
    }
}
