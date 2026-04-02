import java.time.LocalDateTime;

public class ActiveRental {
    private final String bikeID;
    private final String userEmail;
    private final LocalDateTime tripStartTime;
    private final String location;

    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime, String location) {
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.tripStartTime = tripStartTime;
        this.location = location;
    }

    public String getBikeID() {
        return bikeID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Bike ID: " + bikeID + " User Email: " + userEmail + " Trip Start Time: " + tripStartTime
                + " Location: " + location;
    }
}
