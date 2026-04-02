import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class BikeService {
    private static final Stack<ERyderLog> systemLogStack = new Stack<>();
    private static final Queue<BikeRequest> bikeRequest = new ArrayDeque<>();
    private static final AtomicInteger rentalLogId = new AtomicInteger(156);
    private static final AtomicInteger tripStartLogId = new AtomicInteger(1);
    private static final AtomicInteger tripEndLogId = new AtomicInteger(1);

    private RentalService rentalService;

    public void setRentalService(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public String getBikeLocation(String bikeID) {
        if (bikeID == null) {
            return null;
        }
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                return bike.getLocation();
            }
        }
        return null;
    }

    public String findAvailableBikeAtLocation(String location) {
        return findAvailableBikeAtLocation(location, null);
    }

    public String findAvailableBikeAtLocation(String location, String userEmail) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        if (userEmail != null && !userEmail.isEmpty()) {
            bikeRequest.offer(new BikeRequest(userEmail, location, LocalDateTime.now()));
        }
        return null;
    }

    public boolean validateLocation(String location) {
        return findAvailableBikeAtLocation(location) != null;
    }

    public boolean reserveBike(String bikeID) {
        return reserveBike(bikeID, null, null);
    }

    public boolean reserveBike(String bikeID, String userEmail, String location) {
        if (bikeID == null) {
            System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
            return false;
        }
        String resolvedLocation = location != null ? location : getBikeLocation(bikeID);
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID) && bike.isAvailable()) {
                bike.setAvailable(false);
                LocalDateTime now = LocalDateTime.now();
                bike.setLastUsedTime(now);
                System.out.println("Reserving the bike with the bikeID. Please follow the on-screen instructions to locate the bike and start your pleasant journey.");
                String logId = "BR" + rentalLogId.getAndIncrement();
                String emailPart = userEmail != null && !userEmail.isEmpty() ? userEmail : "user";
                String locPart = resolvedLocation != null ? resolvedLocation : bike.getLocation();
                String event = "Bike with " + bikeID + " was rented by " + emailPart + " from " + locPart;
                pushLogEntry(logId, event, now);
                return true;
            }
        }
        System.out.println("Sorry, selected bike is no longer available.");
        if (userEmail != null && !userEmail.isEmpty() && resolvedLocation != null) {
            bikeRequest.offer(new BikeRequest(userEmail, resolvedLocation, LocalDateTime.now()));
        }
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

    public void logTripStarted(String bikeID, String userEmail, String location) {
        String logId = "TS" + tripStartLogId.getAndIncrement();
        String emailPart = userEmail != null && !userEmail.isEmpty() ? userEmail : "user";
        String locPart = location != null ? location : "";
        String event = "Trip started for bike " + bikeID + " by " + emailPart + " from " + locPart;
        pushLogEntry(logId, event, LocalDateTime.now());
    }

    public void logTripEnded(String bikeID, String userEmail, String location) {
        String logId = "TE" + tripEndLogId.getAndIncrement();
        String emailPart = userEmail != null && !userEmail.isEmpty() ? userEmail : "user";
        String locPart = location != null ? location : "";
        String event = "Trip ended for bike " + bikeID + " by " + emailPart + " at " + locPart;
        pushLogEntry(logId, event, LocalDateTime.now());
    }

    public void removeTrip(String bikeID) {
        if (bikeRequest.isEmpty()) {
            return;
        }
        String location = getBikeLocation(bikeID);
        if (location == null) {
            return;
        }
        BikeRequest next = bikeRequest.peek();
        if (next == null || !location.equals(next.getLocation())) {
            return;
        }
        bikeRequest.poll();
        if (rentalService == null) {
            return;
        }
        String availableId = findAvailableBikeIdQuiet(next.getLocation());
        if (availableId != null) {
            rentalService.startRental(availableId, next.getUserEmail());
        }
    }

    public void viewSystemLogs() {
        for (int i = 0; i < systemLogStack.size(); i++) {
            System.out.println(systemLogStack.get(i).toString());
        }
    }

    public void viewBikeRequestQueue() {
        for (BikeRequest request : bikeRequest) {
            System.out.println(request);
        }
    }

    public void updateQueueRemoveFirst() {
        BikeRequest removed = bikeRequest.poll();
        if (removed != null) {
            System.out.println("Removed from queue: " + removed);
        } else {
            System.out.println("Queue is already empty.");
        }
    }

    private void pushLogEntry(String logId, String event, LocalDateTime timeStamp) {
        systemLogStack.push(new ERyderLog(logId, event, timeStamp));
    }

    private String findAvailableBikeIdQuiet(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                return bike.getBikeID();
            }
        }
        return null;
    }
}
