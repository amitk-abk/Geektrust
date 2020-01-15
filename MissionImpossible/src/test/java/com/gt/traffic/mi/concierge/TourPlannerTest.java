package com.gt.traffic.mi.concierge;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.planet.Planet;
import com.gt.traffic.mi.planet.Ruler;
import com.gt.traffic.mi.planet.Suburb;
import com.gt.traffic.mi.vehicle.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gt.traffic.mi.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.mi.planet.Suburb.RKPURAM;
import static com.gt.traffic.mi.planet.Suburb.SILKDORB;
import static com.gt.traffic.mi.weather.Weather.SUNNY;
import static com.gt.traffic.mi.weather.Weather.WINDY;
import static org.junit.Assert.assertEquals;

public class TourPlannerTest {

    @Test
    public void shouldRecommendPathAndMeansForSunnyWeatherWithTraffic() throws Exception {
        Planet Lengaburu = planetOfLengaburu();

        Map<String, Float> speedLimits = new HashMap<>();
        speedLimits.put("Orbit1", 20f);
        speedLimits.put("Orbit2", 12f);
        speedLimits.put("Orbit3", 15f);
        speedLimits.put("Orbit4", 12f);

        for (Orbit orbit: Lengaburu.getOrbits())
            orbit.currentTrafficSpeed(speedLimits.getOrDefault(orbit.getName(), 0f));

        TourPlanner tourPlanner = TourPlanner.planner(Lengaburu);
        assertEquals("Vehicle Tuktuk to Hallitharam via Orbit1 and R K Puram via Orbit4",
                tourPlanner.recommendationForJourney(SUNNY, SILKDORB, RKPURAM, HALLITHARAM));
    }

    @Test
    public void shouldRecommendPathAndMeansForWindyWeatherWithTraffic() throws Exception {
        Planet Lengaburu = planetOfLengaburu();

        Map<String, Float> speedLimits = new HashMap<>();
        speedLimits.put("Orbit1", 5f);
        speedLimits.put("Orbit2", 10f);
        speedLimits.put("Orbit3", 20f);
        speedLimits.put("Orbit4", 20f);

        for (Orbit orbit: Lengaburu.getOrbits())
            orbit.currentTrafficSpeed(speedLimits.getOrDefault(orbit.getName(), 0f));

        TourPlanner tourPlanner = TourPlanner.planner(Lengaburu);
        assertEquals("Vehicle Car to R K Puram via Orbit3 and Hallitharam via Orbit4",
                tourPlanner.recommendationForJourney(WINDY, SILKDORB, HALLITHARAM, RKPURAM));
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
        Orbit orbit3 = orbitAs("Orbit3", 30, 15, SILKDORB, RKPURAM);
        Orbit orbit4 = orbitAs("Orbit4", 15, 18, HALLITHARAM, RKPURAM);
        return Arrays.asList(orbit1, orbit2, orbit3, orbit4);
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
