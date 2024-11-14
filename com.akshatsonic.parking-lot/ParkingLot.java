import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

class ParkingLot{
    private static List<Level> levels;
    private static ParkingLot parkingLotInstance;

    private ParkingLot(List<Level> levels){
        levels = new ArrayList<>();
    }

    public synchronized ParkingLot getParkingLotInstance(){
        if(parkingLotInstance == null)
            parkingLotInstance =  new ParkingLot(levels);
        return parkingLotInstance;
    }

    public List<Level> getLevels(){
        return levels;
    }

    public void addLevel(Level level){
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle){
        for(Level level: levels){
            if(level.)
        }
    }
}