package com.gt.traffic.concierge;

import com.gt.traffic.concierge.TourPlanner;
import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Planet;
import com.gt.traffic.planet.Ruler;
import com.gt.traffic.planet.Suburb;
import com.gt.traffic.vehicle.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;
import static com.gt.traffic.weather.Weather.SUNNY;
import static com.gt.traffic.weather.Weather.WINDY;
import static org.junit.Assert.assertEquals;

public class TourPlannerTest {

    @Test
    public void shouldRecommendPathAndMeansForSunnyWeatherWithTraffic() throws Exception {
        Planet Lengaburu = planetOfLengaburu();

        Map<String, Float> speedLimits = new HashMap<>();
        speedLimits.put("Orbit1", 12f);
        speedLimits.put("Orbit2", 10f);

        for (Orbit orbit: Lengaburu.getOrbits())
            orbit.currentTrafficSpeed(speedLimits.getOrDefault(orbit.getName(), 0f));

        TourPlanner tourPlanner = TourPlanner.planner(Lengaburu);
        assertEquals("Vehicle Tuktuk on Orbit1", tourPlanner.recommendationForJourney(SUNNY, SILKDORB, HALLITHARAM));
    }

    @Test
    public void shouldRecommendPathAndMeansForWindyWeatherWithTraffic() throws Exception {
        Planet Lengaburu = planetOfLengaburu();

        Map<String, Float> speedLimits = new HashMap<>();
        speedLimits.put("Orbit1", 14f);
        speedLimits.put("Orbit2", 20f);

        for (Orbit orbit: Lengaburu.getOrbits())
            orbit.currentTrafficSpeed(speedLimits.getOrDefault(orbit.getName(), 0f));

        TourPlanner tourPlanner = TourPlanner.planner(Lengaburu);
        assertEquals("Vehicle Car on Orbit2", tourPlanner.recommendationForJourney(WINDY, SILKDORB, HALLITHARAM));
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
