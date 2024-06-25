import java.util.Scanner;

public class ATM {
    private User user;
    private Scanner scanner;

    public ATM(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : user.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            user.addTransaction(new Transaction("Withdraw", amount, user.getBalance()));
            System.out.println("Withdrawal successful. New balance: " + user.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            user.addTransaction(new Transaction("Deposit", amount, user.getBalance()));
            System.out.println("Deposit successful. New balance: " + user.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void transfer(User recipient) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            user.addTransaction(new Transaction("Transfer", amount, user.getBalance()));
            recipient.addTransaction(new Transaction("Transfer Received", amount, recipient.getBalance()));
            System.out.println("Transfer successful. New balance: " + user.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }
}
