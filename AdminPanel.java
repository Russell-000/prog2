

import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private final UserService userService;
    private final BikeService bikeService;
    private final RentalService rentalService;

    public AdminPanel() {
        this.userService = new UserService();
        this.bikeService = new BikeService();
        this.rentalService = new RentalService(bikeService);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add user");
            System.out.println("2. Remove user");
            System.out.println("3. Update user");
            System.out.println("4. View all users");
            System.out.println("5. Demo bike rental flow");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    removeUser(scanner);
                    break;
                case 3:
                    updateUser(scanner);
                    break;
                case 4:
                    viewUsers();
                    break;
                case 5:
                    demoBikeRentalFlow(scanner);
                    break;
                case 6:
                    System.out.println("Exiting Admin Panel.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void addUser(Scanner scanner) {
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("User type: ");
        String userType = scanner.nextLine();
        RegisteredUsers user = new RegisteredUsers(fullName, email, null,
                null, null, null, null, userType, new String[0]);
        userService.addUser(user);
        System.out.println("User added.");
    }

    private void removeUser(Scanner scanner) {
        System.out.print("Email to remove: ");
        String email = scanner.nextLine();
        if (userService.removeUser(email)) {
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUser(Scanner scanner) {
        System.out.print("Email to update: ");
        String email = scanner.nextLine();
        System.out.print("New full name: ");
        String newName = scanner.nextLine();
        System.out.print("New user type: ");
        String newType = scanner.nextLine();
        if (userService.updateUser(email, newName, newType)) {
            System.out.println("User updated.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void viewUsers() {
        List<RegisteredUsers> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (RegisteredUsers user : users) {
            System.out.println(user);
        }
    }

    private void demoBikeRentalFlow(Scanner scanner) {
        System.out.print("Is the user registered? (true/false): ");
        boolean isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        if (!isRegisteredUser) {
            System.out.println("You’re not our registered user. Please consider registering.");
        } else {
            System.out.println("Welcome back, " + emailAddress + "!");
        }

        String bikeID = bikeService.findAvailableBikeAtLocation(location);
        if (bikeID == null) {
            return;
        }

        if (rentalService.startRental(bikeID, emailAddress)) {
            System.out.println("Displaying active rentals...");
            rentalService.trackActiveRentals();
            System.out.println("Simulating the end of the trip...");
            rentalService.endRental(bikeID);
            System.out.println("Displaying the active rentals after trip end...");
            rentalService.trackActiveRentals();
        }
    }
}