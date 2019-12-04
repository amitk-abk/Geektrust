package com.gt.traffic.vehicle;

import com.gt.traffic.planet.Orbit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.gt.traffic.planet.Suburb.HALLITHARAM;
import static com.gt.traffic.planet.Suburb.SILKDORB;
import static com.gt.traffic.weather.Weather.*;
import static org.junit.Assert.*;

public class VehicleTest {

    private final double delta = 0.01;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void bikeShouldCover20mmIn2hrsWithNoConstraints() throws Exception {
        assertEquals(2.0, bikeAs("Bike", 10, 2).hrsToTravelMegaMiles(20), delta);
    }

    @Test
    public void tuktukShouldCover24mmIn2hrsWithNoConstraints() throws Exception {
        assertEquals(2.0, tuktukAs("Tuktuk", 12, 1).hrsToTravelMegaMiles(24), delta);
    }

    @Test
    public void superCarShouldCover40mmIn2hrsWithNoConstraints() throws Exception {
        assertEquals(2.0, carAs("Car", 20, 3).hrsToTravelMegaMiles(40), delta);
    }

    @Test
    public void bikeCanNotTravelInRainyWeather() throws Exception {
        Vehicle bike = bikeAs("Bike", 10, 2);
        assertFalse(bike.canTravelIn(RAINY));
        assertTrue(bike.canTravelIn(SUNNY));
        assertTrue(bike.canTravelIn(WINDY));
    }

    @Test
    public void tuktukCanNotTravelInWindyWeather() throws Exception {
        Vehicle tuktuk = tuktukAs("Tuktuk", 12, 1);
        assertFalse(tuktuk.canTravelIn(WINDY));
        assertTrue(tuktuk.canTravelIn(SUNNY));
        assertTrue(tuktuk.canTravelIn(RAINY));
    }

    @Test
    public void superCarCanTravelInAnyWeather() throws Exception {
        Vehicle superCar = carAs("Car", 20, 3);
        assertTrue(superCar.canTravelIn(WINDY));
        assertTrue(superCar.canTravelIn(RAINY));
        assertTrue(superCar.canTravelIn(SUNNY));
    }

    @Test
    public void bikeShouldTakeAdditionalTimeToTravelOnOrbitWithCratersInDifferentWeathers() throws Exception {
        Vehicle bike = bikeAs("Bike", 10, 2);
        Orbit orbit = orbitAs("Orbit", 20, 10);
        orbit.currentTrafficSpeed(bike.hourlySpeed);

        float minimumHrsTaken = bike.hrsToTravelMegaMiles(20);

        float hrsTaken = bike.hrsToTravelOrbitIn(orbit, SUNNY);
        assertEquals(2.29, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);

        hrsTaken = bike.hrsToTravelOrbitIn(orbit, WINDY);
        assertEquals(2.33, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);
    }

    @Test
    public void bikeCanNotBeOperatedOnAnyOrbitInRainyWeather() throws Exception {
        Vehicle bike = bikeAs("Bike", 10, 2);
        Orbit orbit = orbitAs("Orbit", 20, 10);

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Can not travel in rainy weather");
        bike.hrsToTravelOrbitIn(orbit, RAINY);
    }

    @Test
    public void tuktukShouldTakeAdditionalTimeToTravelOnOrbitWithCratersInDifferentWeathers() throws Exception {
        Vehicle tuktuk = tuktukAs("Tuktuk", 12, 1);
        Orbit orbit = orbitAs("Orbit", 20, 10);
        orbit.currentTrafficSpeed(tuktuk.hourlySpeed);

        float minimumHrsTaken = tuktuk.hrsToTravelMegaMiles(20);

        float hrsTaken = tuktuk.hrsToTravelOrbitIn(orbit, SUNNY);
        assertEquals(1.81, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);

        hrsTaken = tuktuk.hrsToTravelOrbitIn(orbit, RAINY);
        assertEquals(1.86, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);
    }

    @Test
    public void tuktukCanNotBeOperatedOnOrbitInWindyWeather() throws Exception {
        Vehicle tuktuk = tuktukAs("Tuktuk", 12, 1);
        Orbit orbit = orbitAs("Orbit", 20, 10);

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Can not travel in windy weather");
        tuktuk.hrsToTravelOrbitIn(orbit, WINDY);
    }

    @Test
    public void carShouldTakeAdditionalTimeToTravelOnOrbitWithCratersInDifferentWeathers() throws Exception {
        Vehicle car = carAs("Car", 20, 3);
        Orbit orbit = orbitAs("Orbit", 20, 10);
        orbit.currentTrafficSpeed(car.hourlySpeed);

        float minimumHrsTaken = car.hrsToTravelMegaMiles(20);

        float hrsTaken = car.hrsToTravelOrbitIn(orbit, SUNNY);
        assertEquals(1.45, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);

        hrsTaken = car.hrsToTravelOrbitIn(orbit, RAINY);
        assertEquals(1.60, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);

        hrsTaken = car.hrsToTravelOrbitIn(orbit, WINDY);
        assertEquals(1.5, hrsTaken, delta);
        assertTrue(Float.compare(minimumHrsTaken, hrsTaken) < 0);
    }


    private Bike bikeAs(String name, float hourlySpeed, float minutesToCrossCrater) {
        return new Bike(name, hourlySpeed, minutesToCrossCrater);
    }

    private Tuktuk tuktukAs(String name, float hourlySpeed, float minutesToCrossCrater) {
        return new Tuktuk(name, hourlySpeed, minutesToCrossCrater);
    }

    private SuperCar carAs(String name, float hourlySpeed, float minutesToCrossCrater) {
        return new SuperCar(name, hourlySpeed, minutesToCrossCrater);
    }

    private Orbit orbitAs(String name, float lengthInMM, int craters) {
        return new Orbit.As(name)
                .length(lengthInMM)
                .craters(craters)
                .from(SILKDORB)
                .to(HALLITHARAM)
                .init();
    }
}
