package com.example.java.product;

import com.example.java.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Deposit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private Date creationDate = Calendar.getInstance().getTime();

    /* Getters and setters */
    public UUID getOwnerAccountId() {
        return ownerAccountId;
    }

    public double getBalance() {
        return balance;
    }

    private double balance;

    public Deposit(double startMoney, UUID ownerAccountId) {
        id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        balance = startMoney;
    }
    @Override
    public void manageInterest() {

    }

    @Override
    public UUID getId() {
        return id;
    }
}
