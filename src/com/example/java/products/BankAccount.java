package com.example.java.products;

import com.example.java.Bank;
import com.example.java.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BankAccount implements Product {
    /* Declarations */
    final private UUID id;
    public UUID getId() {
        return id;
    }

    final private UUID ownerId;
    public UUID getOwnerId() {
        return ownerId;
    }

    final private Date creationDate = Calendar.getInstance().getTime();
    public Date getCreationDate() {
        return creationDate;
    }

    private double balance;
    public double getBalance() {
        return balance;
    }

    private final Bank bank;
    public Bank getBank() {
        return bank;
    }

    private final ArrayList<String> accountHistory = new ArrayList<>();
    public ArrayList<String> getAccountHistory() {
        return accountHistory;
    }

    private boolean isOverdraft = false;
    private double maxOverdraft = 0;
    public void setOverdraft(boolean isOverdraft, double maxOverdraft) {
        this.isOverdraft = isOverdraft;
        this.maxOverdraft = maxOverdraft;
    }

    public enum percentageType {
        DAILY,
        MONTHLY,
        ANNUALLY
    }
    private percentageType percentageType;
    private double percentageNumber;
    private Date lastPercentageDate;

    public void setLastPercentageDate(Date lastPercentageDate) {
        this.lastPercentageDate = lastPercentageDate;
    }

    public void setPercentage(percentageType percentageType, double number) {
        this.percentageType = percentageType;
        if(number < 1 && number >0 ){
            this.percentageNumber = number;
        }
        else{
            this.percentageNumber = 0.02;
        }
    }

    /* Constructor */
    public BankAccount(Bank bank, UUID ownerId, double balance, percentageType percentageType, double percentageNumber) {
        this.balance = balance;
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.setPercentage(percentageType, percentageNumber);
        this.bank = bank;
        this.lastPercentageDate = this.getCreationDate();
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

    public void countPercentage(){
        Date date = new Date();
        switch(this.percentageType){
            case DAILY:
                if (TimeUnit.DAYS.convert(date.getTime()- lastPercentageDate.getTime(), TimeUnit.MILLISECONDS) > 1) {
                    this.balance += this.balance * this.percentageNumber;
                    this.lastPercentageDate = date;
                }
            case MONTHLY:
                if (TimeUnit.DAYS.convert(date.getTime()- lastPercentageDate.getTime(), TimeUnit.MILLISECONDS) > 30) {
                    this.balance += this.balance * this.percentageNumber;
                    this.lastPercentageDate = date;
                }
            case ANNUALLY:
                if (TimeUnit.DAYS.convert(date.getTime()- lastPercentageDate.getTime(), TimeUnit.MILLISECONDS) > 365) {
                    this.balance += this.balance * this.percentageNumber;
                    this.lastPercentageDate = date;
                }
        }
    }
}
