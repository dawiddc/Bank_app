import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    /* Declarations */
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private ArrayList<ArrayList<String>> bankHistory = new ArrayList<>();

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void createBankAccount() {
        bankAccounts.add(new BankAccount(this));
    }

    public void removeBankAccount(UUID toBeRemovedId) {
        BankAccount accountToBeRemoved = findBankAccount(toBeRemovedId);
        if (accountToBeRemoved != null)
            bankAccounts.remove(accountToBeRemoved);
    }

    public BankAccount findBankAccount(UUID id) {
        for (BankAccount bankAccount : bankAccounts)
            if (bankAccount.getId().equals(id))
                return bankAccount;
        return null;
    }

    public String getReport() {
        String report = "Couldn't find information...";

        return report;
    }
}
