package com.example.java.products;

import com.example.java.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Deposit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private int months;
    final private double yearPercentage;
    final private Date creationDate = Calendar.getInstance().getTime();

    /* Getters and setters */
    public UUID getOwnerAccountId() {
        return ownerAccountId;
    }

    public double getBalance() {
        return balance;
    }

    public double getYearPercentage() {
        return yearPercentage;
    }

    public int getMonths() {
        return months;
    }

    private double balance;

    public Deposit(double startMoney, UUID ownerAccountId, int months, double yearPercentage) {
        this.id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        this.balance = startMoney;
        this.months = months;
        this.yearPercentage = yearPercentage;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public void terminateDeposit(){

    }
    @Override
    public void manageInterest() {

    }

    @Override
    public UUID getId() {
        return id;
    }
}
