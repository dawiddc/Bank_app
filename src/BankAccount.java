import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class BankAccount implements Product {
    /* Declarations */
    final private UUID id;
    private double balance;
    private Bank bank;
    private ArrayList<String> accountHistory = new ArrayList<>();
    private boolean isOverdraft = false;
    private double maxOverdraft = 0;

    /* Getters and setters */
    public UUID getId() { return id; }
    public double getBalance() { return balance; }
    public ArrayList<String> getAccountHistory() { return accountHistory; }
    public void setOverdraft(boolean isOverdraft, double maxOverdraft) {
        this.isOverdraft = isOverdraft;
        this.maxOverdraft = maxOverdraft;
    }

    /* Constructor */
    public BankAccount(Bank bank) {
        id = UUID.randomUUID();
        this.bank = bank;
    }

    /* methods */
    public void manageOverdraft() {

    }

    public void countInterest() {

    }

    public void subtractMoney(double amount) {
        if (hasEnoughMoney(amount)) {
            balance -= amount;
            logOperation(this.id, -amount);
        }

    }

    private boolean hasEnoughMoney(double amount) {
        if(amount < balance)
            return true;
        if(amount > balance) {
            return isOverdraft && (balance + maxOverdraft) > amount;
        }
        return false;
    }

    public void addMoney(double amount) {
        logOperation(this.id, amount);
        balance += amount;
    }

    public void transferMoney(double amount, UUID receiverID) {
        if (bank.findBankAccount(receiverID) != null && hasEnoughMoney(amount)) {
            balance -= amount;
            logOperation(this.id, -amount);

            BankAccount receiver = bank.findBankAccount(receiverID);
            receiver.addMoney(amount);
            logOperation(receiver.getId(), amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }

    public void logOperation(UUID id, double amount) {
        Date date = new Date();
        accountHistory.add(date.getTime() + " - " + amount);
        bank.getBankHistory().add(date.getTime() + " - " + id + " - " + amount);
    }
}
