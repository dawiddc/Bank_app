package com.example.java.products;

import com.example.java.interests.AccountInterestState;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Credit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private Date creationDate = Calendar.getInstance().getTime();
    private AccountInterestState state = null;

    /* Getters and setters */
    public UUID getOwnerAccountId() {
        return ownerAccountId;
    }

    public double getBalance() {
        return balance;
    }

    public Credit(double startMoney, UUID ownerAccountId, AccountInterestState state) {
        this.id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        this.balance = startMoney;
        this.state = state;
    }

    private double balance;

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
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
    public void addMoney(double amount) {
        balance += amount;
    }
}
