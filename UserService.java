import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<RegisteredUsers> users = new ArrayList<>();

    public void addUser(RegisteredUsers user) {
        users.add(user);
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
