import vehicles.Vehicle;
import vehicles.VehicleType;

import java.util.Scanner;

public class ParkingLotDriver {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();

        parkingLot.addLevel(new Level(0, 10));
        parkingLot.addLevel(new Level(1, 5));

        Scanner scannerObj =  new Scanner(System.in);

        while(true){
            System.out.println("OPTIONS:-");
            System.out.println("p -> park vehicle \nu -> unpark vehicle \na -> availability for vehicle type");
            String option = scannerObj.nextLine();
            switch (option){
                case "p":
                    parkingLot.parkVehicle(getVehicleFromUser());
                    break;
                case "u":
                    parkingLot.unparkVehicle(getVehicleFromUser());
                    break;
                case "a":
                    System.out.println("parking spots available: "+parkingLot.getAvailableParkingSpots(getVehicleTypeFromUser()));
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

     static Vehicle getVehicleFromUser(){
        Scanner scannerObj =  new Scanner(System.in);
        System.out.print("Vehicle Number: ");
        int vehicleNumber = scannerObj.nextInt();
        VehicleType vehicleType = getVehicleTypeFromUser();
        return new Vehicle(
                vehicleNumber,
                vehicleType
        );
    }

    static VehicleType getVehicleTypeFromUser(){
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Vehicle Type: \nc -> car\nb -> bike \nt -> truck");
        String vehicleType = scannerObj.nextLine();
        return switch (vehicleType){
            case "c":
                yield VehicleType.CAR;
            case "b":
                yield VehicleType.BIKE;
            case "t":
                yield VehicleType.TRUCK;

            default:
                throw new IllegalStateException("Unexpected value: " + vehicleType);
        };
    }
}
