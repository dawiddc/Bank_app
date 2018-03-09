import java.util.UUID;

public class BankAccount extends Product {
    private UUID id;
    private double balance;
    private Bank bank;

    public BankAccount(Bank bank) {
        id = UUID.randomUUID();
        this.bank = bank;
    }

    public void manageDebet() {

    }

    public void countPercentage() {

    }

    public void withdrawMoney(Double amount) {
        balance -= amount;
    }

    public void addMoney(Double amount) {
        balance += amount;
    }

    public void transferMoney(Double amount, UUID receiverID) {
        balance -= amount;
    }
}
