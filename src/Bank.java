import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void createBankAccout() {
        bankAccounts.add(new BankAccount(this));
    }
}
