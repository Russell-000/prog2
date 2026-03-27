package com.example.graded.exercise.one;

import java.util.Scanner;

public class AdminPanel {
    // Existing fields and methods (assumed to be present)
    // ...

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. View all registered users");
            System.out.println("2. View all feedback");
            System.out.println("3. Demo the Bike Rental System");   // New option
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View registered users (existing code)
                    break;
                case 2:
                    // View feedback (existing code)
                    break;
                case 3:
                    // Call the bike rental simulation
                    BikeRental rentalSim = new BikeRental();
                    rentalSim.simulateApplicationInput();
                    break;
                case 4:
                    System.out.println("Exiting Admin Panel.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Other existing methods...
}