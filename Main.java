public class Main {
    public static void main(String[] args) {
        // Create object using default constructor and print details
        ERyder bike1 = new ERyder();
        System.out.println("Details of bike created with default constructor:");
        bike1.printBikeDetails();
        System.out.println();

        // Create object using parameterized constructor, call ride(), then print details
        ERyder bike2 = new ERyder(101, 75, true, 120.5);
        System.out.println("Calling ride() for bike2:");
        bike2.ride();
        System.out.println("\nDetails of bike2 after ride check:");
        bike2.printBikeDetails();
    }
}