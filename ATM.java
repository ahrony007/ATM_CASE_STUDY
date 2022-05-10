// ATM.java
package atmImplementation;

public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen; 
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;
    
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
    }
    
    // start ATM
    public void run() {
        
        // welcome and authenticate user; perform transactions
        while(true) {
            
            // loop while user is not yet authenticated
            while(!userAuthenticated) {
                screen.displayMessage("\nWelcome");
                authenticateUser();
            }
            
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine( "\nThank you! Goodbye!" );
        }
    }
    
    // attempts to authenticate user against database
    private void authenticateUser(){
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getInput();
        
        // set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        
        if(userAuthenticated){
            currentAccountNumber = accountNumber;
        }else{
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }
    
    // display the main menu and perform transactions
    private void performTransactions() {
        
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;
        boolean userExited = false;
        
        // loop while user has not chosen option to exit system
        while(!userExited) {
            int mainMenuSelection = displayMainMenu();
            
            // decide how to proceed based on user's menu selection
            switch(mainMenuSelection) {
                case BALANCE_INQUIRY:
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                  
                case WITHDRAWAL:
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case DEPOSIT:
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                 
                case EXIT:
                    screen.displayMessageLine( "\nExiting the system..." );
                    userExited = true;
                    break;
                default: 
                    screen.displayMessageLine( "\nYou did not enter a valid selection. Try again!" );
                    break;
            }
        }
    }
    
    // display the main menu and return an input selection
    private int displayMainMenu(){
        screen.displayMessageLine( "\nMain menu:" );
        screen.displayMessageLine( "1 - View my balance" );
        screen.displayMessageLine( "2 - Withdraw cash" );
        screen.displayMessageLine( "3 - Deposit funds" );
        screen.displayMessageLine( "4 - Exit\n" );
        screen.displayMessageLine( "Enter a choice: " );
        return keypad.getInput();
    }
    
    // return object of specified Transaction subclass
    private Transaction  createTransaction(int type) {
        Transaction temp = null;
        
        // determine which type of Transaction to create
        switch(type) {
            case BALANCE_INQUIRY: // create new BalanceInquiry transaction
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase );
                break;
            case WITHDRAWAL: // create new Withdrawal transaction
                temp = new Withdrawal( currentAccountNumber, screen, bankDatabase, keypad, cashDispenser );
                break;
            case DEPOSIT: // create new Deposit transaction
                temp = new Deposit( currentAccountNumber, screen, bankDatabase, keypad, depositSlot );
                break;
        }
        return temp;
    }
}
