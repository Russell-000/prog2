public class Eryder2
{
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private String bikeID;
    private double batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    private double totalUsageInMinutes;
    private double totalFare;

   
    public String getBikeID() 
    {
        return bikeID;
    }

    public Eryder2(String linkedAccount, String linkedPhoneNumber) 
    {
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        this.bikeID = "111111";
        this.batteryLevel = 100.0;
        this.isAvailable = true;
        this.kmDriven = 0.0;
        this.totalUsageInMinutes = 0.0;
        this.totalFare = 0.0;
    }
    public Eryder2(String bikeID, double batteryLevel, boolean isAvailable, double kmDriven,String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        this.totalUsageInMinutes = 0.0;
        this.totalFare = 0.0;
    }

   
    public String getLinkedAccount() { return LINKED_ACCOUNT; }
    public String getLinkedPhoneNumber() { return LINKED_PHONE_NUMBER; }
    public double gettotalUsageInMinutes() { return totalUsageInMinutes; }
    public double getTotalFare() { return totalFare; }
    // Getters for other fields can be added as needed
    public void printRideDetails(double totalusageInMinutes)
    {
        System.out.println("Company Name: " + COMPANY_NAME);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("Kilometers Driven: " + kmDriven + " km");
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Total Usage in Minutes: " + totalUsageInMinutes + " minutes");
        System.out.println("Total Fare: $" + totalFare);
    }
    public double calculateFare(double totalusageInMinutes) 
    {
        this.totalUsageInMinutes += totalusageInMinutes;
        this.totalFare = BASE_FARE + (PER_MINUTE_FARE * totalusageInMinutes);
        return this.totalFare;
    }
}
