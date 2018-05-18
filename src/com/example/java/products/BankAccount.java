package com.example.java.products;

import com.example.java.Bank;
import com.example.java.interests.AccountInterestState;
import com.example.java.operations.CountInterests;
import com.example.java.operations.Operation;

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
    private final ArrayList<Operation> accountHistory = new ArrayList<>();
    private double balance;
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
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public ArrayList<Operation> getAccountHistory() {
        return accountHistory;
    }

    /* Methods */
    public void manageInterest() {
        CountInterests countInterests = new CountInterests(state, this);
        logOperation(countInterests);
    }

    public void doOperation(Operation operation) {
        operation.execute();
        logOperation(operation);
    }

    public void subtractMoney(double amount) {
        if (hasEnoughMoney(amount)) {
            balance -= amount;
        }
    }

    public boolean hasEnoughMoney(double amount) {
        return amount <= balance;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public void logOperation(Operation operation) {
        accountHistory.add(operation);
        bank.getBankHistory().add(operation);
    }

}
