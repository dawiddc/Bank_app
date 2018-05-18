package com.example.java.products;

import com.example.java.Bank;
import com.example.java.interests.AccountInterestState;
import com.example.java.operations.Operation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Deposit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private Bank bank;
    final private int months;
    final private Date creationDate;
    private final ArrayList<Operation> depositHistory = new ArrayList<>();

    private double balance;
    private AccountInterestState state = null;

    public Deposit(Bank bank, double startMoney, UUID ownerAccountId, int months, AccountInterestState state) {
        this.id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        this.balance = startMoney;
        this.months = months;
        this.bank = bank;
        this.state = state;
        this.creationDate = Calendar.getInstance().getTime();
    }

    /* Getters and setters */
    public UUID getOwnerAccountId() {
        return ownerAccountId;
    }

    public AccountInterestState getState() {
        return state;
    }

    public void setState(AccountInterestState state) {
        this.state = state;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getMonths() {
        return months;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    @Override
    public void subtractMoney(double amount) {
        balance -= amount;
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return amount < balance;
    }

    public void returnMoney(double amount, UUID receiverID) {
        Product receiverAccount = bank.findBankProductByID(receiverID);
        if (receiverAccount != null) {
            balance -= amount;

            receiverAccount.addMoney(amount);
        } else {
            System.out.println("Could not find account to return money");
        }
    }

    @Override
    public void manageInterest() {
        state.countInterests(this);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void doOperation(Operation operation) {
        operation.execute();
        logOperation(operation);
    }

    public void logOperation(Operation operation) {
        depositHistory.add(operation);
        bank.getBankHistory().add(operation);
    }

}
