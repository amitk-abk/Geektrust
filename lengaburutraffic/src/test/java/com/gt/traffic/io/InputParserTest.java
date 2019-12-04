package com.gt.traffic.io;

import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Planet;
import com.gt.traffic.planet.Ruler;
import com.gt.traffic.planet.Suburb;
import com.gt.traffic.vehicle.*;
import com.gt.traffic.weather.Weather;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;
import static com.gt.traffic.weather.Weather.SUNNY;
import static org.junit.Assert.assertEquals;

public class InputParserTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private InputParser parse = new InputParser();
    private Planet lengaburu = planetOfLengaburu();

    @Test
    public void shouldParseWeatherCondition() throws Exception {
        Weather weather = parse.weatherFrom("Weather is Sunny");
        assertEquals(SUNNY, weather);
    }

    @Test
    public void shouldThrowExceptionWhenWeatherConditionCouldNotBeDetermined() throws Exception {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Weather could not be determined from input");
        parse.weatherFrom("Weather is Munny");
    }

    @Test
    public void shouldParseOrbit() throws Exception {
        Orbit orbit = parse.orbitFrom("Orbit1 traffic speed is 15 megamiles/hour", lengaburu);
        assertEquals(orbitsInLengaburu().get(0), orbit);
    }

    @Test
    public void shouldThrowExceptionWhenOrbitCouldNotBeDetermined() throws Exception {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Orbit could not be determined from input");
        parse.orbitFrom("Orbit traffic speed is 15 megamiles/hour", lengaburu);
    }

    @Test
    public void shouldParseOrbitSpeed() throws Exception {
        String speed = parse.speedFrom("Orbit1 traffic speed is 15 megamiles/hour");
        assertEquals("15", speed);
    }

    @Test
    public void shouldThrowExceptionWhenOrbitTrafficCouldNotBeDetermined() throws Exception {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Orbit traffic could not be determined from input");
        parse.speedFrom("Orbit1 traffic speed is XX megamiles/hour");
    }

    private Planet planetOfLengaburu() {
        return new Planet.Of("Lengaburu")
                .ruler(rulerOfLengaburu())
                .orbits(orbitsInLengaburu())
                .init();
    }

    private Ruler rulerOfLengaburu() {
        Ruler ruler = new Ruler("King Shan");
        ruler.hasFleet(new Fleet(fleet()));
        return ruler;
    }

    private List<Orbit> orbitsInLengaburu() {
        Orbit orbit1 = orbitAs("Orbit1", 18, 20, SILKDORB, HALLITHARAM);
        Orbit orbit2 = orbitAs("Orbit2", 20, 10, SILKDORB, HALLITHARAM);
        return Arrays.asList(orbit1, orbit2);
    }

    private Orbit orbitAs(String name, float lengthInMM, int craters, Suburb from, Suburb to) {
        return new Orbit.As(name)
                .length(lengthInMM)
                .craters(craters)
                .from(from)
                .to(to)
                .init();
    }

    private List<Vehicle> fleet() {
        return Arrays.asList(new Bike("Bike", 10, 2),
                new Tuktuk("Tuktuk", 12, 1),
                new SuperCar("Car", 20, 3));
    }
}
