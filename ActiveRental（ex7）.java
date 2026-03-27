package com.example.graded.exercise.one;

import java.time.LocalDateTime;
import java.util.*;

public class ActiveRental {
    private String bikeID;
    private String userEmail;
    private LocalDateTime tripStartTime;
    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime) {
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.tripStartTime = tripStartTime;
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
    public String toString()
    {
        return "Bike ID: "+bikeID+" User Email: "+userEmail+" Trip Start Time: "+tripStartTime;
    }
    
}
