// Account.java
// Represents a bank account

package atmImplementation;

public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;
    
    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance ) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }
    
    // determines whether a user-specified PIN matches PIN in Account
    public boolean validatePIN(int userPIN) {
        if ( userPIN == pin ){
            return true;
        }else{
            return false;
        }
    }
    
    // returns available balance
    public double getAvailableBalance() {
        return availableBalance;
    }
    
    // returns the total balance
    public double getTotalBalance() {
        return totalBalance;
    }
    
    // credits an amount to the account
    public void credit(double amount) {
        totalBalance += amount;
    }
    
    // debits an amount from the account
    public void debit(double amount){
        availableBalance -= amount;
        totalBalance -= amount;
    }

    // returns account number
    public int getAccountNumber() {
        return accountNumber;
    }
}
