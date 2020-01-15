package com.gt.traffic.mi.planet;

import com.gt.traffic.mi.vehicle.Fleet;
import com.gt.traffic.mi.vehicle.Vehicle;

public class Ruler {

    private final String name;
    private Fleet fleet;

    public Ruler(String name) {
        this.name = name;
    }

    public Fleet fleet() {
        return fleet;
    }

    public void hasFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    public int preferenceOf(Vehicle vehicle) {
        switch (vehicle.vehicleName()) {
            case "Bike":
                return 1;
            case "Tuktuk":
                return 2;
            case "Car":
                return 3;
            default:
                return -1;
        }
    }
}
