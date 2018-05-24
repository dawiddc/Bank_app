package com.example.java.products;

import com.example.java.Bank;
import com.example.java.interests.AccountInterestState;
import com.example.java.operations.CountInterests;
import com.example.java.operations.Operation;
import com.example.java.reporting.Visitor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Credit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private Date creationDate = Calendar.getInstance().getTime();
    private final ArrayList<Operation> creditHistory = new ArrayList<>();
    private final Bank bank;
    private AccountInterestState state = null;
    private double balance;

    public Credit(double startMoney, Bank bank, UUID ownerAccountId, AccountInterestState state) {
        this.id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        this.balance = startMoney;
        this.state = state;
        this.bank = bank;
    }

    /* Getters and setters */
    public UUID getOwnerAccountId() {
        return ownerAccountId;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void manageInterest() {
        CountInterests countInterests = new CountInterests(state, this);
        countInterests.execute();
        logOperation(countInterests);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void addMoney(double amount) {
        balance += amount;
    }

    @Override
    public void subtractMoney(double amount) {
        balance -= amount;
    }

    // TODO: Implement credit methods
    @Override
    public boolean hasEnoughMoney(double amount) {
        return false;
    }

    @Override
    public void doOperation(Operation operation) {
        operation.execute();
        logOperation(operation);
    }

    public void logOperation(Operation operation) {
        creditHistory.add(operation);
        bank.getBankHistory().add(operation);
    }

    @Override
    public Product accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
