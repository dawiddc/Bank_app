package com.example.java.products;

import com.example.java.Bank;
import com.example.java.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.Date;

public class BankAccount implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerId;
    final private Date creationDate = Calendar.getInstance().getTime();
    private double balance = 0;
    private final Bank bank;
    private final ArrayList<String> accountHistory = new ArrayList<>();
    private boolean isOverdraft = false;
    private double maxOverdraft = 0;

    /* Getters and setters */
    public UUID getId() { return id; }
    public double getBalance() { return balance; }
    public ArrayList<String> getAccountHistory() { return accountHistory; }
    public UUID getOwnerId() { return ownerId; }
    public Date getCreationDate() { return creationDate; }
    public void setOverdraft(boolean isOverdraft, double maxOverdraft) {
        this.isOverdraft = isOverdraft;
        this.maxOverdraft = maxOverdraft;
    }

    /* Constructor */
    public BankAccount(Bank bank, UUID ownerId) {
        id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.bank = bank;
    }

    /* Methods */
    public void manageInterest() {

    }

    public void subtractMoney(double amount) {
        if (hasEnoughMoney(amount)) {
            balance -= amount;
            logOperation(this.id, -amount);
        }

    }

    private boolean hasEnoughMoney(double amount) {
        if (amount < balance)
            return true;
        if (amount > balance) {
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

    private void logOperation(UUID id, double amount) {
        Date date = new Date();
        accountHistory.add(Calendar.getInstance().getTime() + " - " + amount);
        bank.getBankHistory().add(Calendar.getInstance().getTime() + " - " + id + " - " + amount);
    }
}
