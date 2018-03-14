import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class BankAccount extends Product {
    /* Declarations */
    final private UUID id;
    private double balance;
    private Bank bank;

    /* Getters and setters */
    public UUID getId() { return id; }
    public double getBalance() {return balance; }
    private ArrayList<String> accountHistory = new ArrayList<>();

    /* Constructor */
    public BankAccount(Bank bank) {
        id = UUID.randomUUID();
        this.bank = bank;
    }

    /* methods */
    public void manageDebet() {

    }

    public void countPercentage() {

    }

    public void subtractMoney(Double amount) {
        logOperation(this.id, -amount);
        balance -= amount;
    }

    public void addMoney(Double amount) {
        logOperation(this.id, amount);
        balance += amount;
    }

    public void transferMoney(Double amount, UUID receiverID) {
        if ( bank.findBankAccount(receiverID) != null ) {
            balance -= amount;
            logOperation(this.id, -amount);

            bank.findBankAccount(receiverID).addMoney(amount);
            logOperation(this.id, amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }

    public void logOperation(UUID id, Double amount) {
        Date date = new Date();
        accountHistory.add(date.getTime() + " - " + id + " - " + amount);
    }
}
