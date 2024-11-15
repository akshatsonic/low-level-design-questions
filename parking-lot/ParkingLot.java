import vehicles.Vehicle;
import vehicles.VehicleType;

import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private static List<Level> levels;
    private static ParkingLot parkingLotInstance;

    private ParkingLot(List<Level> levels) {
        this.levels =levels;
    }

    public static synchronized ParkingLot getParkingLotInstance() {
        if (parkingLotInstance == null)
            parkingLotInstance = new ParkingLot(new ArrayList<>());
        return parkingLotInstance;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                System.out.println("Vehicle with number: " + vehicle.getVehicleNumber() + " parked successfully");
                return true;
            }
        }
        System.out.println("No vacant spots, please wait!!");
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.unparkVehicle(vehicle)) {
                System.out.println("Vehicle with number: " + vehicle.getVehicleNumber() + " unparked successfully");
                return true;
            }
        }
        System.out.println("Unable to find vehicle number: " + vehicle.getVehicleNumber());
        return false;
    }

    public long getAvailableParkingSpots(VehicleType vehicleType){
        long parkingSpots = 0;
        for(Level level : levels){
            parkingSpots += level.getCountOfAvailableParkingSpots(vehicleType);
        }
        return parkingSpots;
    }
}