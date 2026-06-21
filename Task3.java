import java.util.Scanner;

// ==========================================
// 4. BANK ACCOUNT CLASS
// ==========================================
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        // Simple safety check to ensure account doesn't start negative
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited: $%.2f\n", amount);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        // 6. Validate user input for sufficient balance
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return false;
        } else if (amount > balance) {
            System.out.println("Transaction Failed: Insufficient funds.");
            return false;
        } else {
            balance -= amount;
            System.out.printf("Successfully withdrew: $%.2f\n", amount);
            return true;
        }
    }
}

// ==========================================
// 1 & 2. ATM MACHINE & USER INTERFACE CLASS
// ==========================================
class ATM {
    // 5. Connect the ATM class with the user's bank account
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Starts the ATM user interface loop
    public void start() {
        int choice;
        do {
            displayMenu();
            System.out.print("Choose an option: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-4).");
                System.out.print("Choose an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            System.out.println(); // Formatting spacing

            // 3. Implement methods/actions for each option
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        } while (choice != 4);
        
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n========= ATM MENU =========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("============================");
    }

    private void checkBalance() {
        System.out.printf("Current Account Balance: $%.2f\n", account.getBalance());
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.deposit(amount); // 7. Display appropriate success/failure messages
        } else {
            System.out.println("Error: Invalid numerical amount.");
            scanner.next(); 
        }
    }

    private void handleWithdrawal() {
        System.out.print("Enter withdrawal amount: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.withdraw(amount); // 7. Display appropriate success/failure messages
        } else {
            System.out.println("Error: Invalid numerical amount.");
            scanner.next(); 
        }
    }
}

// ==========================================
// MAIN EXECUTION CLASS
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Initialize user's bank account with a starting balance of $500.00
        BankAccount userAccount = new BankAccount(500.00);
        
        // Initialize ATM UI and link it to the user's account
        ATM atmMachine = new ATM(userAccount);
        
        // Boot up the ATM interface
        atmMachine.start();
    }
}
