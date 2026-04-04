public class RegularUser extends RegisteredUsers {

    public RegularUser(String fullName, String emailAddress, String dateOfBirth, String cardNumber,
                       String cardExpiryDate, String cardProvider, String cvv, String userType,
                       String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv,
                userType, lastThreeTrips);
    }

    @Override
    public double calculateFare(double baseFare) {
        return super.calculateFare(baseFare);
    }

    @Override
    public void displayUserType() {
        super.displayUserType();
    }
}
