package com.example.java;

import com.example.java.products.BankAccount;
import com.example.java.products.Deposit;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    /* Declarations */
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private final ArrayList<String> bankHistory = new ArrayList<>();
    private final ArrayList<Deposit> deposits = new ArrayList<>();

    /* Getters and setters */
    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    public ArrayList<String> getBankHistory() {
        return bankHistory;
    }
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    /* Methods */
    public void createBankAccount() {
        UUID owner = UUID.randomUUID();
        bankAccounts.add(new BankAccount(this, owner, 0, BankAccount.percentageType.DAILY, 0.02));
    }

    public void removeBankAccount(UUID toBeRemovedId) {
        BankAccount accountToBeRemoved = findBankAccountByID(toBeRemovedId);
        if (accountToBeRemoved != null)
            bankAccounts.remove(accountToBeRemoved);
    }

    public BankAccount findBankAccountByID(UUID id) {
        for (BankAccount bankAccount : bankAccounts)
            if (bankAccount.getId().equals(id))
                return bankAccount;
        return null;
    }
    public Deposit findBanDepositByID(UUID id) {
        for (Deposit deposit : deposits)
            if (deposit.getId().equals(id))
                return deposit;
        return null;
    }

    public String createAccountHistoryReport(UUID id) {
        String report = "Couldn't find account history information...";
        ArrayList<String> accountHistory = findBankAccountByID(id).getAccountHistory();
        report = buildHistoryReport(report, accountHistory);
        return report;
    }

    public String createBankHistoryReport() {
        String report = "Couldn't find bank history information...";
        report = buildHistoryReport(report, bankHistory);
        return report;
    }

    private String buildHistoryReport(String report, ArrayList<String> history) {
        if (!history.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String s : history) {
                sb.append(s);
                sb.append("\t");
            }
            report = sb.toString();
        }
        return report;
    }
    public void createDeposit(UUID ownerAccountID, double startMoney, int months, double yearPercentage){
        deposits.add(new Deposit(startMoney, ownerAccountID, months, yearPercentage));
        BankAccount bankAccount = this.findBankAccountByID(ownerAccountID);
        bankAccount.transferDeposit(startMoney, deposits.get(deposits.size() - 1).getId());
    }
}
