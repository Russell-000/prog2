/**
 * Represents an e-bike in the eRyder sharing service.
 */
public class ERyder {
    private int bikeID;
    private int batteryLevel;      // in percentage (0-100)
    private boolean isAvailable;
    private double kmDriven;        // total distance travelled in km

    // Default constructor
    public ERyder() {
        // No initialization – values will be set via setters or remain default
    }

    // Parameterized constructor
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel); // use setter to validate
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    // ride() – checks availability and battery level
    public void ride() {
        if (isAvailable && batteryLevel > 0) {
            System.out.println("Bike is available for a ride.");
        } else {
            System.out.println("Bike is not available.");
        }
    }

    // printBikeDetails() – displays all bike information
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Kilometers Driven: " + kmDriven + " km");
    }

    // Getters and Setters
    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    // Setter with validation (0-100)
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level. Must be between 0 and 100.");
            // Optionally set to a default or keep previous value
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}