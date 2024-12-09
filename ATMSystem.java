import java.util.Scanner;

class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else if (amount <= 0) {
            return false; 
        }
        balance -= amount;
        return true;
    }
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }
}
class ATM {
    private BankAccount account;
    public ATM(BankAccount account) {
        this.account = account;
    }
    public void displayMenu() {
        System.out.println("\n-------- ATM Menu --------");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }
    public void checkBalance() {
        System.out.printf("Your current balance is: %.2f\n", account.getBalance());
    }
    public void deposit(double amount) {
        if (account.deposit(amount)) {
            System.out.printf("%.2f has been deposited to your account.\n", amount);
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }
    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.printf("%.2f has been withdrawn from your account.\n", amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds. Please try again.");
        }
    }
}
public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000.0);
        ATM atm = new ATM(account);
        boolean exit = false;
        while (!exit) {
            atm.displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: 
                    atm.checkBalance();
                    break;
                case 2: 
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3: 
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4: 
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }
}
