package com.example.java.products;

import com.example.java.Bank;
import com.example.java.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Deposit implements Product {
    /* Declarations */
    final private UUID id;
    final private UUID ownerAccountId;
    final private Bank bank;
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

    public Deposit(Bank bank, double startMoney, UUID ownerAccountId, int months, double yearPercentage) {
        this.id = UUID.randomUUID();
        this.ownerAccountId = ownerAccountId;
        this.balance = startMoney;
        this.months = months;
        this.yearPercentage = yearPercentage;
        this.bank = bank;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public void checkState(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.creationDate);
        cal.add(Calendar.MONTH, this.months);
        Date tempDate = cal.getTime();
        if(TimeUnit.DAYS.convert(date.getTime(), TimeUnit.MILLISECONDS) > TimeUnit.DAYS.convert(tempDate.getTime(), TimeUnit.MILLISECONDS)) {
            this.returnMoney(getBalance(), getOwnerAccountId());
            double percentage = this.months/12 * this.yearPercentage;
            this.returnMoney(getBalance()* percentage,getOwnerAccountId());
        }

    }

    public void returnMoney(double amount, UUID receiverID) {
        BankAccount receiverAccount = bank.findBankAccountByID(receiverID);
        if (receiverAccount != null) {
            balance -= amount;

            receiverAccount.addMoney(amount);
            receiverAccount.logOperation(receiverAccount.getId(), amount);
        } else {
            System.out.println("Could not find account to return money");
        }
    }
    @Override
    public void manageInterest() {

    }

    @Override
    public UUID getId() {
        return id;
    }
}
