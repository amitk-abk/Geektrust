package com.gt.traffic.concierge;

import com.gt.traffic.planet.Orbit;
import com.gt.traffic.planet.Suburb;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;
import static org.junit.Assert.assertEquals;

public class RouteFinderTest {

    @Test
    public void shouldFindRoutesBetweenSourceAndDestination() throws Exception {
        RouteFinder routeFinder = new RouteFinder(orbits());

        List<List<String>> routes = new ArrayList<>();
        routes.add(Arrays.asList(SILKDORB.suburbName(), "Orbit1", HALLITHARAM.suburbName()));
        routes.add(Arrays.asList(SILKDORB.suburbName(), "Orbit2", HALLITHARAM.suburbName()));

        List<List<String>> allRoutes = routeFinder.findBetween(SILKDORB, HALLITHARAM);
        assertEquals(routes, allRoutes);
    }

    private List<Orbit> orbits() {
        Orbit orbit1 = orbitAs("Orbit1", 10, 10, SILKDORB, HALLITHARAM);
        Orbit orbit2 = orbitAs("Orbit2", 20, 20, SILKDORB, HALLITHARAM);
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
}
