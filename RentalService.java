import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RentalService {
    private final List<ActiveRental> activeRentals = new LinkedList<>();
    private final BikeService bikeService;

    public RentalService(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    public boolean startRental(String bikeID, String emailAddress) {
        String location = bikeService.getBikeLocation(bikeID);
        if (!bikeService.reserveBike(bikeID, emailAddress, location)) {
            return false;
        }
        bikeService.logTripStarted(bikeID, emailAddress, location);
        ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, LocalDateTime.now(), location);
        activeRentals.add(activeRental);
        return true;
    }

    public boolean endRental(String bikeID) {
        ActiveRental ended = null;
        for (ActiveRental rental : activeRentals) {
            if (rental.getBikeID().equals(bikeID)) {
                ended = rental;
                break;
            }
        }
        boolean removed = removeActiveRental(bikeID);
        boolean released = bikeService.releaseBike(bikeID);
        if (ended != null) {
            bikeService.logTripEnded(bikeID, ended.getUserEmail(), ended.getLocation());
        }
        if (removed && released) {
            System.out.println("Your trip has ended. Thank you for using our service. We hope to see you again soon!");
            bikeService.removeTrip(bikeID);
            return true;
        }
        return false;
    }

    public boolean cancelRental(String bikeID) {
        boolean removed = removeActiveRental(bikeID);
        bikeService.releaseBike(bikeID);
        return removed;
    }

    public List<ActiveRental> getActiveRentals() {
        return new LinkedList<>(activeRentals);
    }

    public void trackActiveRentals() {
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental rental : activeRentals) {
            System.out.println(rental);
        }
    }

    private boolean removeActiveRental(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentals.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
