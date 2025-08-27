package fleetmanagement;

// Inheritance: Truck extends Vehicle
public class Truck extends Vehicle {
    private double capacity; // tons

    public Truck(String plateNumber, String model, double capacity) {
        super(plateNumber, model);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String info() {
        return "Truck " + getModel() + " [" + getPlateNumber() + "], Capacity: " + capacity + " tons";
    }
}
