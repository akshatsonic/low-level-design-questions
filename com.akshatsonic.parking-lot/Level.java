import vehicles.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {
    private final int floor;
    private final List<ParkingSpot> parkingSpots;

    public Level(int floor, int numSpots){
        this.floor=floor;
        parkingSpots = new ArrayList<>(numSpots);

        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numBikes = (int)(numSpots * spotsForBikes);
        int numCars = (int)(numSpots * spotsForCars);

        for(int i=1;i<=numBikes;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.BIKE));
        }
        for(int i=numBikes+1;i<=numCars;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.CAR));
        }
        for(int i=numBikes+numCars+1;i<=numSpots;i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public List<ParkingSpot> getListOfParkingSpots(){
        return this.parkingSpots;
    }

    public long getCountOfAvailableParkingSpots(VehicleType vehicleType){
        return this.parkingSpots.stream()
                .filter(ParkingSpot::isAvailable)
                .filter(parkingSpot -> parkingSpot.getVehicleType().equals(vehicleType))
                .count();
    }
}
