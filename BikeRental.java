

import java.util.Scanner;

public class BikeRental {
    private final BikeService bikeService = new BikeService();
    private final RentalService rentalService = new RentalService(bikeService);

    public BikeRental() {
        bikeService.setRentalService(rentalService);
    }

    public void simulateApplicationInput()
    {
        System.out.println("This is the simulation of the e-bike rental process.");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Is the user registered? (true/false): ");
        boolean isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");

        if (!isRegisteredUser) {
            System.out.println("You’re not our registered user. Please consider registering.");
        } else {
            System.out.println("Welcome back, " + emailAddress + "!");
        }

        String bikeID = bikeService.findAvailableBikeAtLocation(location, emailAddress);

        if (bikeID == null) return;

        System.out.println("Simulating e-bike reservation…");
        if (rentalService.startRental(bikeID, emailAddress)) {
            System.out.println("Displaying the active rentals…");
            rentalService.trackActiveRentals();
            System.out.println("Simulating the end of the trip…");
            rentalService.endRental(bikeID);
            System.out.println("Displaying the active rentals after trip end…");
            rentalService.trackActiveRentals();
        }
    }
}