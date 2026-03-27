package com.example.graded.exercise.one;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;

public class BikeRental {
    private Boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private Boolean locationValid;

    private UserRegistration userReg = new UserRegistration();
    private ActiveRental activeRental;

    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void simulateApplicationInput()
    {
        System.out.println("This is the simulation of the e-bike rental process.");

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Is the user registered? (true/false): ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter email address: ");
        emailAddress = scanner.nextLine();

        System.out.print("Enter location: ");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");

        bikeID=analyseRequest(isRegisteredUser, emailAddress, location);

        if(locationValid==false) return;
        System.out.println("Simulating e-bike reservation…");
        reverseBike(bikeID);
        System.out.println("Displaying the active rentals…");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
    }
    private void reverseBike(String bikeID2) {
        
    }
    private String analyseRequest(Boolean isRegisteredUser, String emailAddress, String location)
    {
        if(isRegisteredUser==false)
        {
            System.out.println("You’re not our registered user. Please consider registering.");
            return userReg.registration(location);
        }
        else
        {
            System.out.println("Welcome back, " + emailAddress + "!");
        }
    }
    
    private String validateLocation(String location)
    {
        for(Bike bike: BikeDatabase.bikes)
        {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
    }
    private String reserveBike(String bikeID)
    {
        if(bikeID!=null)
        {
            for(Bike bike : BikeDatabase.bikes)
            {
                if(bike.getBikeID().equals(bikeID))
                {
                    tripStartTime=LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println(" Reserving the bike with the (bikeID). Please following the on-screen instructions\r\n" + //
                                                "to locate the bike and start your pleasant journey.");
                    activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalList.add(activeRental);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.\r\n");
            return null;
        }
    }
    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }
    private String removeTrip(String bikeID)
    {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while(iterator.hasNext())
        {
            ActiveRental rental = iterator.next();
            if(rental.getBikeID().equals(bikeID))
            {
                iterator.remove();
                break;
            }
        }
        for(Bike bike: BikeDatabase.bikes)
        {
            if(bike.getBikeID().equals(bikeID))
            {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for using our service. We hope to see you again soon!");
                break;
            }
        }
    }
}