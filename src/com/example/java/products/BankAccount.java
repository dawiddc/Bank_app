package com.example.java.products;

import com.example.java.Bank;
import com.example.java.interests.AccountInterestState;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class BankAccount implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerId;
    final private Date creationDate = Calendar.getInstance().getTime();
    private final Bank bank;
    private final ArrayList<String> accountHistory = new ArrayList<>();
    private double balance;
    private boolean isOverdraft = false;
    private double maxOverdraft = 0;
    private AccountInterestState state = null;

    /* Constructor */
    public BankAccount(Bank bank, UUID ownerId, double balance, AccountInterestState state) {
        this.balance = balance;
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.bank = bank;
        this.state = state;
    }

    public AccountInterestState getState() {
        return state;
    }

    public void setState(AccountInterestState state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public ArrayList<String> getAccountHistory() {
        return accountHistory;
    }

    public void setOverdraft(boolean isOverdraft, double maxOverdraft) {
        this.isOverdraft = isOverdraft;
        this.maxOverdraft = maxOverdraft;
    }

    /* Methods */
    public void manageInterest() {
        state.countInterests(this);
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
        if (bank.findBankAccountByID(receiverID) != null && hasEnoughMoney(amount)) {
            balance -= amount;
            logOperation(this.id, -amount);

            BankAccount receiver = bank.findBankAccountByID(receiverID);
            receiver.addMoney(amount);
            logOperation(receiver.getId(), amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }

    public void transferDeposit(double amount, UUID depositID) {
        if (bank.findBanDepositByID(depositID) != null && hasEnoughMoney(amount)) {
            balance -= amount;
            logOperation(this.id, -amount);

            Deposit deposit = bank.findBanDepositByID(depositID);
            deposit.addMoney(amount);
            logOperation(deposit.getId(), amount);
        } else {
            System.out.println("Could not find deposit");
        }
    }

    public void logOperation(UUID id, double amount) {
        Date date = new Date();
        accountHistory.add(Calendar.getInstance().getTime() + " - " + amount);
        bank.getBankHistory().add(Calendar.getInstance().getTime() + " - " + id + " - " + amount);
    }
}
