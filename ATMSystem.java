import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private Map<String, User> users;
    private Scanner scanner;

    public ATMSystem() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        // Adding some users for testing
        users.put("user1", new User("user1", "1234", 1000));
        users.put("user2", new User("user2", "5678", 2000));
    }

    public void start() {
        System.out.println("Welcome to the ATM System");

        User currentUser = authenticateUser();
        if (currentUser != null) {
            ATM atm = new ATM(currentUser);
            boolean quit = false;
            while (!quit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Select an option: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        atm.showTransactionHistory();
                        break;
                    case 2:
                        atm.withdraw();
                        break;
                    case 3:
                        atm.deposit();
                        break;
                    case 4:
                        transferFunds(atm);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank you for using the ATM System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    private User authenticateUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        } else {
            return null;
        }
    }

    private void transferFunds(ATM atm) {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.next();
        User recipient = users.get(recipientId);
        if (recipient != null) {
            atm.transfer(recipient);
        } else {
            System.out.println("Recipient user ID not found.");
        }
    }
}

