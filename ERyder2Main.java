public class ERyder2Main {
    public static void main(String[] args) {
        
        Eryder2 bike1 = new Eryder2("guest_user", "555-0000");

       
        Eryder2 bike2 = new Eryder2("EB2002", 92.0, true, 89.7,
                                    "alice_smith", "555-9876");

       
        System.out.println("===== Ride1 Details (after 25 min ride) =====");
        bike1.calculateFare(25);          
        bike1.printRideDetails(25);        

        System.out.println("\n===== Ride2 Details (after 42 min ride) =====");
        bike2.calculateFare(42);
        bike2.printRideDetails(42);

        
        int minutes = 30;
        double fare = bike2.calculateFare(minutes); 
        System.out.println("\n===== Additional Fare Calculation =====");
        System.out.println("Another ride of " + minutes + " minutes costs: $" + fare);
        System.out.println("Total usage for bike2 now: " + bike2.gettotalUsageInMinutes() + " minutes");
    }
}