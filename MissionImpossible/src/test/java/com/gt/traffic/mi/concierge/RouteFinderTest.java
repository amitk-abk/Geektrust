package com.gt.traffic.mi.concierge;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.planet.Suburb;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gt.traffic.mi.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.mi.planet.Suburb.RKPURAM;
import static com.gt.traffic.mi.planet.Suburb.SILKDORB;
import static org.junit.Assert.assertEquals;

public class RouteFinderTest {

    @Test
    public void shouldFindRoutesBetweenSourceAndDestination() throws Exception {
        RouteFinder routeFinder = new RouteFinder(orbits());

        List<List<String>> routes = new ArrayList<>();
        routes.add(Arrays.asList(SILKDORB.suburbName(), "Orbit1", HALLITHARAM.suburbName()));
        routes.add(Arrays.asList(SILKDORB.suburbName(), "Orbit3", RKPURAM.suburbName(), "Orbit4", HALLITHARAM.suburbName()));
        routes.add(Arrays.asList(SILKDORB.suburbName(), "Orbit2", HALLITHARAM.suburbName()));

        List<List<String>> allRoutes = routeFinder.findBetween(SILKDORB, HALLITHARAM);
        assertEquals(routes, allRoutes);
    }

    private List<Orbit> orbits() {
        Orbit orbit1 = orbitAs("Orbit1", 10, 10, SILKDORB, HALLITHARAM);
        Orbit orbit2 = orbitAs("Orbit2", 20, 20, SILKDORB, HALLITHARAM);
        Orbit orbit3 = orbitAs("Orbit3", 30, 15, SILKDORB, RKPURAM);
        Orbit orbit4 = orbitAs("Orbit4", 15, 18, RKPURAM, HALLITHARAM);
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
}
