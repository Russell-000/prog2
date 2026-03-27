import java.util.*;

public class AdminPanel {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        int choice;
        do {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Exiting Admin Panel.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void addNewUsers() {
        System.out.print("How many users would you like to add? ");
        int count = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Entering details for user " + (i+1) + " ---");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExpiry = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] trips = new String[3];
            for (int t = 0; t < 3; t++) {
                System.out.println("\n--- Trip " + (t+1) + " ---");
                System.out.print("Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Source and Destination (e.g., Source: NJIT Gate 5, Destination: Wending Square): ");
                String route = scanner.nextLine();

                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();

                System.out.print("Feedback (can be NULL): ");
                String feedback = scanner.nextLine();

                StringBuilder tripBuilder = new StringBuilder();
                tripBuilder.append("Date: ").append(date)
                           .append(", Source: ").append(route.split(",")[0].trim())  // simplistic; better to store as given
                           .append(", Destination: ").append(route.split(",")[1].trim())
                           .append(", Fare (€): ").append(fare)
                           .append(", Feedback: ").append(feedback);
                trips[t] = tripBuilder.toString();
            }

            RegisteredUsers newUser = new RegisteredUsers(fullName, email, dob, cardNumber,
                    cardExpiry, cardProvider, cvv, userType, trips);
            registeredUsersList.add(newUser);
            System.out.println("User added successfully.");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display.");
            return;
        }
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
            System.out.println("Last Three Trips:");
            String[] trips = user.getLastThreeTrips();
            for (String trip : trips) {
                System.out.println("  " + trip);
            }
            System.out.println("-----------------------------");
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove.");
            return;
        }
        System.out.print("Enter email address of user to remove: ");
        String email = scanner.nextLine();

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("No user found with this email address.");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update.");
            return;
        }
        System.out.print("Enter email address of user to update: ");
        String email = scanner.nextLine();

        RegisteredUsers targetUser = null;
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                targetUser = user;
                break;
            }
        }
        if (targetUser == null) {
            System.out.println("No user found with this email address.");
            return;
        }

        System.out.println("\n--- Updating user details (press ENTER to keep current value) ---");
        System.out.print("New full name (current: " + targetUser.getFullName() + "): ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()) {
            targetUser.setFullName(newFullName);
        }

        System.out.print("New email address (current: " + targetUser.getEmailAddress() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            targetUser.setEmailAddress(newEmail);
        }

        System.out.print("New date of birth (current: " + targetUser.getDateOfBirth() + "): ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) {
            targetUser.setDateOfBirth(newDob);
        }

        System.out.print("New card number (enter '0' for no change, current: " + targetUser.getCardNumber() + "): ");
        String newCardNumber = scanner.nextLine();
        if (!newCardNumber.equals("0")) {
            targetUser.setCardNumber(newCardNumber);
        }

        System.out.print("New card provider (current: " + targetUser.getCardProvider() + "): ");
        String newCardProvider = scanner.nextLine();
        if (!newCardProvider.isEmpty()) {
            targetUser.setCardProvider(newCardProvider);
        }

        System.out.print("New card expiry date (current: " + targetUser.getCardExpiryDate() + "): ");
        String newCardExpiry = scanner.nextLine();
        if (!newCardExpiry.isEmpty()) {
            targetUser.setCardExpiryDate(newCardExpiry);
        }

        System.out.print("New CVV (enter '0' for no change, current: " + targetUser.getCvv() + "): ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0")) {
            targetUser.setCvv(newCvv);
        }

        System.out.print("New user type (current: " + targetUser.getUserType() + "): ");
        String newUserType = scanner.nextLine();
        if (!newUserType.isEmpty()) {
            targetUser.setUserType(newUserType);
        }

        System.out.println("User details updated successfully.");
    }
}