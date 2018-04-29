package OO_Design;

public class ParkingLot {
    enum VehicleType {
        REGULAR,
        HANDICAPPED,
        COMPACT
    }

    class ParkingSpace{
        VehicleType type;
        boolean isOccupied;
    }

    class Vehicle{
        VehicleType type;
        ParkingSpace occupied;
    }

    private ParkingSpace[] spaces = new ParkingSpace[100];
    private int remain = spaces.length;

    private ParkingSpace findNearest(VehicleType type){
        if(remain == 0) return null;
        for(ParkingSpace parkingSpace : spaces){
            if(!parkingSpace.isOccupied && parkingSpace.type == type) return parkingSpace;
        }
        return null;
    }

    public void Park(Vehicle vehicle, ParkingSpace parkingSpace){
        ParkingSpace next = findNearest(vehicle.type);
        if(next == null) return;
        remain--;
        next.isOccupied = true;
        vehicle.occupied = parkingSpace;
    }

    public void release(Vehicle vehicle){
        remain++;
        vehicle.occupied.isOccupied = false;
        vehicle.occupied = null;
    }

    public boolean isFull(){
        return remain > 0;
    }
}
