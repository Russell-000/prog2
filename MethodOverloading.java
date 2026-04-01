public class MethodOverloading 
{
    public static double salary=calculateSalary();
    public final String FIRSTNAME;
    public int x=10;
     public static double calculateSalary() {
       return 50000.0; // Example fixed salary
    }
    public int age;
    public MethodOverloading(int age) {
        this.age = age;
        System.out.println("Age set to: " + this.age);
    }
}
