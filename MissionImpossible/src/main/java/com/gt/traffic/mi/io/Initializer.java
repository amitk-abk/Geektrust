package com.gt.traffic.mi.io;

import com.gt.traffic.mi.planet.Orbit;
import com.gt.traffic.mi.planet.Suburb;
import com.gt.traffic.mi.vehicle.Bike;
import com.gt.traffic.mi.vehicle.SuperCar;
import com.gt.traffic.mi.vehicle.Tuktuk;
import com.gt.traffic.mi.vehicle.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Initializer {

    private String file;
    private List<Orbit> orbits;
    private List<Vehicle> vehicles;

    public Initializer(String file) {
        this.file = file;
        this.orbits = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        initialize();
    }

    public List<Orbit> orbitsInLengaburu() {
        return this.orbits;
    }

    public List<Vehicle> fleet() {
        return this.vehicles;
    }

    private void initialize() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream(file)))) {

            String input = reader.readLine();

            while (input.startsWith("Orbit")) {
                String[] orbitDetails = input.split(Pattern.quote(","));
                String orbitName = orbitDetails[1].trim();
                float orbitLengthInMM = Float.parseFloat(orbitDetails[2].trim());
                int cratersOnOrbit = Integer.parseInt(orbitDetails[3].trim());
                Suburb from = suburbFrom(orbitDetails[4].trim());
                Suburb to = suburbFrom(orbitDetails[5].trim());
                orbits.add(orbitAs(orbitName, orbitLengthInMM, cratersOnOrbit, from, to));
                input = reader.readLine();
            }

            while (input.startsWith("Vehicle")) {
                String[] vehicleDetails = input.split(Pattern.quote(","));
                vehicles.add(vehicleFrom(vehicleDetails));
                input = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Opps, something went wrong regarding initialization file.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not initialize from initialization file, " + e.getMessage());
        }
    }

    private Vehicle vehicleFrom(String[] vehicleDetails) {
        String vehicleName = vehicleDetails[1].trim();
        float hourlySpeed = Float.valueOf(vehicleDetails[2].trim());
        float minutesToCrossCrater = Float.valueOf(vehicleDetails[3].trim());
        switch (vehicleName) {
            case "Bike":
                return new Bike(vehicleName, hourlySpeed, minutesToCrossCrater);
            case "TukTuk":
                return new Tuktuk(vehicleName, hourlySpeed, minutesToCrossCrater);
            case "Car":
                return new SuperCar(vehicleName, hourlySpeed, minutesToCrossCrater);
        }
        throw new RuntimeException("Could not initialize vehicles from initialization file.");
    }

    private Suburb suburbFrom(String suburb) {
        Optional<Suburb> sbrb = Stream.of(Suburb.values())
                .filter(suburb1 -> suburb1.suburbName().equals(suburb))
                .findFirst();
        return sbrb.orElseThrow(() -> new RuntimeException("Could not initialize suburb"));
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
