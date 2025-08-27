package fleetmanagement;

// Inheritance: Car extends Vehicle
public class Car extends Vehicle {
    private int seats;

    public Car(String plateNumber, String model, int seats) {
        super(plateNumber, model);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String info() {
        return "Car " + getModel() + " [" + getPlateNumber() + "], Seats: " + seats;
    }
}
