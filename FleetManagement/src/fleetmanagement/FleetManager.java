package fleetmanagement;

import java.util.ArrayList;
import java.util.List;

// Manages a collection of Vehicle objects (objects communicating)
public class FleetManager {
    private final List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        fleet.add(v);
    }

    public void removeByPlate(String plate) {
        fleet.removeIf(v -> v.getPlateNumber().equalsIgnoreCase(plate));
    }

    public List<Vehicle> getFleet() {
        return fleet;
    }
}
