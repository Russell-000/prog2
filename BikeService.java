import java.time.LocalDateTime;

public class BikeService {
    public String findAvailableBikeAtLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    public boolean validateLocation(String location) {
        return findAvailableBikeAtLocation(location) != null;
    }

    public boolean reserveBike(String bikeID) {
        if (bikeID == null) {
            System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
            return false;
        }
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID) && bike.isAvailable()) {
                bike.setAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Reserving the bike with the bikeID. Please follow the on-screen instructions to locate the bike and start your pleasant journey.");
                return true;
            }
        }
        System.out.println("Sorry, selected bike is no longer available.");
        return false;
    }

    public boolean releaseBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }
}
