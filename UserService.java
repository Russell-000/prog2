import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<RegisteredUsers> users = new ArrayList<>();

    public void addUser(RegisteredUsers user) {
        users.add(user);
    }

    /**
     * Creates a VIPUser or RegularUser based on userType and adds it to the list.
     *
     * @return the created user instance (for polymorphic fare calculation elsewhere)
     */
    public RegisteredUsers addNewUsers(String fullName, String emailAddress, String dateOfBirth,
                                       String cardNumber, String cardExpiryDate, String cardProvider,
                                       String cvv, String userType, String[] lastThreeTrips) {
        RegisteredUsers newUser;
        if (userType != null && userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate,
                    cardProvider, cvv, userType, lastThreeTrips);
        } else {
            newUser = new RegularUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate,
                    cardProvider, cvv, userType, lastThreeTrips);
        }
        users.add(newUser);
        return newUser;
    }

    public boolean removeUser(String emailAddress) {
        return users.removeIf(user -> user.getEmailAddress().equalsIgnoreCase(emailAddress));
    }

    public boolean updateUser(String emailAddress, String newName, String newUserType) {
        for (RegisteredUsers user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(emailAddress)) {
                user.setFullName(newName);
                user.setUserType(newUserType);
                return true;
            }
        }
        return false;
    }

    public List<RegisteredUsers> getAllUsers() {
        return new ArrayList<>(users);
    }
}
