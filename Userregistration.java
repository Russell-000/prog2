import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Userregistration 
{
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY=25.0;
    private static final double VIP_DISCOUNT_UNDER_18=20.0;
    private static final double VIP_BASE_FEE=100.0;
   
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid=false;
    private boolean minorAndBirthday=false;
    private boolean minor=false;
    private boolean ageValid=false;
    private boolean cardNumberValid=false;
    private boolean cardStillValid=false;
    private boolean validCVV=false;
    public Userregistration(String fullName, String emailAddress, String dateOfBirth, long cardNumber, String cardProvider, String cardExpiryDate, int cvv) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardProvider = cardProvider;
        this.cardExpiryDate = cardExpiryDate;
        this.cvv = cvv;
    }
    public void registration()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        String choice = scanner.nextLine().trim();
        if("1".equals(choice))
        {
            userType="Regular user";
        }
        else if ("2".equals(choice))
        {
            userType="VIP user";
        }
        else
        {
            System.out.println("Invalid choice.");
        }
        System.out.print("Enter your full name: ");
        fullName = scanner.nextLine().trim();

        System.out.print("Enter your email address: ");
        emailAddress = scanner.nextLine().trim();
        emailValid = analyseEmail(emailAddress);

        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine().trim();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("Enter your card number: ");
        cardNumber = scanner.nextLong();
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.println("Enter your  card expiry date: ");
        cardExpiryDate = scanner.next().trim();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.println("Enter your CVV: ");
        cvv = scanner.nextInt();
        validCVV = analyseCVV(cvv);
        
        finalCheckpoint();

        scanner.close();
    }
    
    private boolean analyseCardNumber(long cardNumber) 
    {
        String cardNumStr = Long.toString(cardNumber);
        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")) 
        {
            cardProvider = "VISA";
            return true;
        }
        else if (cardNumStr.length() == 16) 
        {
            int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
            int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        
            if ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720)) 
            {
                cardProvider = "MasterCard";
                return true;
            }
        }
        else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) 
        {
            cardProvider = "American Express";
            return true;
        }
        System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
        System.out.println("Going back to the start of the registration.");
        registration(); 

        return false;
    }
    private boolean analyseEmail(String email) {
        if(email.contains("@") && email.contains(".") ) 
        {
            System.out.println("Email address is valid.");
            return true;
        }
        else
        {
            System.out.println("Invalid email address.");
            return false;
        }
    }
    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - dob.getYear();
        if(age<12 || age>120)
        {
            System.out.println("Looks like you are either too young or already dead. Sorry, you cannot be our user. Have a nice day.");
            return false;
        }
        else if("VIP User".equals(userType)) {
            if (age<18 && age>12) 
            {
                if (currentDate.getMonthValue() == dob.getMonthValue() && currentDate.getDayOfMonth() == dob.getDayOfMonth()) 
                {
                    System.out.println("Happy Birthday!\r\n" +"You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                    minorAndBirthday=true;
                }
                else
                {
                    System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                    minor=true;
                }
            }
            
        } 
        return true;
    }
    private boolean analyseCardExpiryDate(String cardExpiryDate) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        int month=Integer.parseInt(cardExpiryDate.substring(0, 2));
        int year=Integer.parseInt(cardExpiryDate.substring(3, 5))+2000; 
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        if (year > currentYear || (year == currentYear && month >= currentMonth)) 
        {
            System.out.println("The card is still valid");
            return true;
        } 
        else 
        {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start of the registration process…");
            registration(); 
            return false; 
        }
    }
    private boolean analyseCVV(int cvv) 
    {
        String cvvStr = Integer.toString(cvv);
        if(("American Express".equals(cardProvider)&&cvvStr.length()==4)||("VISA".equals(cardProvider)&&cvvStr.length()==3)||("MasterCard".equals(cardProvider)&&cvvStr.length()==3))
        {
            System.out.println("CVV is valid.");
            return true;
        }
        else
        {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration(); 
            return false;
        }
    }
    private void finalCheckpoint()
    {
        if(emailValid && ageValid && cardNumberValid && cardStillValid && validCVV)
        {
            chargefees();
        }
        else
        {
            System.out.println("Sorry, but some of your information is invalid. Please try again.");
            if(!emailValid)
            {
                System.out.println("Invalid email address.");
            }
            else if(!ageValid)
            {
                System.out.println("Invalid age.");
            }
            else if(!cardNumberValid)
            {
                System.out.println("Invalid card number.");
            }
            else if(!cardStillValid)
            {
                System.out.println("Card has expired.");
            }
            else if(!validCVV)
            {
                System.out.println("Invalid CVV.");
            }
        }
        registration();
    }
    private void chargefees()
    {
        if(minorAndBirthday==true)
        {
            feeToCharge= VIP_BASE_FEE*0.75;
        }
        if(minor==true)
        {
            feeToCharge= VIP_BASE_FEE*0.80;
        }
        else
        {
            feeToCharge=VIP_BASE_FEE;
        }
        String cardStr = Long.toString(cardNumber);
        String lastFour = cardStr.substring(cardStr.length() - 4);

        System.out.printf("Thank you for your payment.%nA fee of %.2f has been charged to your card ending with %s%n",feeToCharge, lastFour);
    }

public String toString() {
    String cardNumberStr = Long.toString(cardNumber);
    

    String censoredPart;
    String lastFourDigits;
    if (cardNumberStr.length() > 4) {
        censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
    } else {
        censoredPart = cardNumberStr.replaceAll(".", "*");
        lastFourDigits = "";
    }
    String censoredNumber = censoredPart + lastFourDigits;

    return "Registration successful! Here are your details:\n" +
           "User Type: " + userType + "\n" +
           "Full Name: " + fullName + "\n" +
           "Email Address: " + emailAddress + "\n" +
           "Date of Birth: " + dateOfBirth + "\n" +
           "Card Number: " + censoredNumber + "\n" +
           "Card Provider: " + cardProvider + "\n" +
           "Card Expiry Date: " + cardExpiryDate;
    }
}
