package com.example.java;

import com.example.java.products.BankAccount;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    /* Declarations */
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private final ArrayList<String> bankHistory = new ArrayList<>();

    /* Getters and setters */
    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    public ArrayList<String> getBankHistory() {
        return bankHistory;
    }

    /* Methods */
    public void createBankAccount() {
        UUID owner = UUID.randomUUID();
        bankAccounts.add(new BankAccount(this, owner));
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

    public String createAccountHistoryReport(UUID id) {
        String report = "Couldn't find account history information...";
        ArrayList<String> accountHistory = findBankAccount(id).getAccountHistory();
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
}
