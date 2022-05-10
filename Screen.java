
// Screen.java
// Represents the screen of the ATM

package atmImplementation;
public class Screen {
    
    // display a message without a carriage return
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    // display a message with a carriage return
    public void displayMessageLine(String message) {
        System.out.println(message);
    }
    
    // displays a dollar amount
    public void displayDollarAmount(double amount) {
        System.out.printf("$%,.2f", amount);
    }
}
