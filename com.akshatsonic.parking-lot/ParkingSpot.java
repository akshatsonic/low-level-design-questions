import vehicles.Vehicle;
import vehicles.VehicleType;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private final VehicleType vehicleType;
    private final int spotNumber;

    public ParkingSpot(int spotNumber, VehicleType vehicleType){
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle(){
        return parkedVehicle;
    }

    public int getSpotNumber(){
        return spotNumber;
    }

    public Integer getParkedVehicleNumber(){
        return parkedVehicle!=null ? parkedVehicle.getVehicleNumber() : null;
    }

    public synchronized boolean isAvailable(){
        return parkedVehicle == null;
    }

    public synchronized void parkVehicle(Vehicle vehicle){
        if(isAvailable() && vehicleType.equals(vehicle.getVehicleType())){
            parkedVehicle = vehicle;
        }
        else {
            throw new IllegalArgumentException("Invalid request type or spot is occupied");
        }
    }

    public synchronized void unparkVehicle(){
        parkedVehicle = null;
    }
}
