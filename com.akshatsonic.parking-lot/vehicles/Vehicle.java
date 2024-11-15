package vehicles;

public class Vehicle {
    private int vehicleNumber;
    private VehicleType vehicleType;

    public Vehicle(int vehicleNumber, VehicleType vehicleType){
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public int getVehicleNumber(){
        return vehicleNumber;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public boolean equals(Vehicle vehicle){
        return vehicle.getVehicleNumber() == this.vehicleNumber && vehicle.getVehicleType().equals(this.vehicleType);
    }
}
