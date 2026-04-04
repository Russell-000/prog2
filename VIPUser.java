public class VIPUser extends RegisteredUsers {

    public VIPUser(String fullName, String emailAddress, String dateOfBirth, String cardNumber,
                   String cardExpiryDate, String cardProvider, String cvv, String userType,
                   String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv,
                userType, lastThreeTrips);
    }

    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 0.8;
    }

    @Override
    public void displayUserType() {
        System.out.println("VIP User");
    }
}
