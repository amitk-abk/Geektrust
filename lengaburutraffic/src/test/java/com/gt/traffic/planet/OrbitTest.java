package com.gt.traffic.planet;

import org.junit.Test;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;
import static com.gt.traffic.weather.Weather.RAINY;
import static com.gt.traffic.weather.Weather.SUNNY;
import static com.gt.traffic.weather.Weather.WINDY;
import static org.junit.Assert.assertEquals;

public class OrbitTest {

    @Test
    public void shouldReturnModifiedCraterCountAsPerWeather() throws Exception {
        Orbit orbit = getOrbit();

        assertEquals(12, orbit.cratersInWeather(RAINY));
        assertEquals(10, orbit.cratersInWeather(WINDY));
        assertEquals(9, orbit.cratersInWeather(SUNNY));
    }

    private Orbit getOrbit() {
        return new Orbit.As("Orbit1")
                .length(20)
                .craters(10)
                .from(SILKDORB)
                .to(HALLITHARAM)
                .init();
    }
}
