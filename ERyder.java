public class ERyder {
    private int bikeID;
    private int batteryLevel;      
    private boolean isAvailable;
    private double kmDriven;        
    // Default constructor
    public ERyder() 
    {
        this.bikeID = 0;  
        this.batteryLevel = 100;  
        this.isAvailable = true; 
        this.kmDriven = 0.0; 
    }
        


    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) 
    {
        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel); // validate
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }


    public void ride() 
    {
        if (isAvailable && batteryLevel > 0) 
        {
            System.out.println("Bike is available for a ride.");
        } 
        else 
        {
            System.out.println("Bike is not available.");
        }
    }


    public void printBikeDetails() 
    {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Kilometers Driven: " + kmDriven + " km");
    }

    public int getBikeID() 
    {
        return bikeID;
    }

    public void setBikeID(int bikeID) 
    {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() 
    {
        return batteryLevel;
    }

    
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) 
        {
            this.batteryLevel = batteryLevel;
        } 
        else 
        {
            System.out.println("Invalid battery level. Must be between 0 and 100.");
        }
    }

    public boolean isAvailable() 
    {
        return isAvailable;
    }

    public void setAvailable(boolean available) 
    {
        isAvailable = available;
    }

    public double getKmDriven() 
    {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) 
    {
        this.kmDriven = kmDriven;
    }
}