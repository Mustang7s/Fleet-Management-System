package fleetmanagement;

// Abstraction + Encapsulation
public abstract class Vehicle {
    private String plateNumber;
    private String model;

    protected Vehicle(String plateNumber, String model) {
        this.plateNumber = plateNumber;
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // Polymorphism
    public abstract String info();
}
