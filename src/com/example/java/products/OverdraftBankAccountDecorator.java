package com.example.java.products;

import com.example.java.operations.Operation;

import java.util.UUID;

public class OverdraftBankAccountDecorator implements Product{

    private BankAccount bankAccount;
    private double maxOverdraft = 0;

    public OverdraftBankAccountDecorator(BankAccount bankAccount, double maxOverdraft) {
        this.bankAccount = bankAccount;
        this.maxOverdraft = maxOverdraft;
    }

    @Override
    public void manageInterest() {
        bankAccount.manageInterest();
    }

    @Override
    public UUID getId() {
        return bankAccount.getId();
    }

    @Override
    public void addMoney(double amount) {
        bankAccount.addMoney(amount);
    }

    @Override
    public void subtractMoney(double amount) {
        if(bankAccount.getBalance() + maxOverdraft >= amount){
            bankAccount.setBalance(bankAccount.getBalance() - amount);
        }
        else {
            System.out.println("Subtract amount exceeds balance with max overdraft!");
        }
    }

    @Override
    public boolean hasEnoughMoney(double amount) {
        return bankAccount.getBalance() + maxOverdraft >= amount;
    }

    @Override
    public double getBalance() {
        return bankAccount.getBalance();
    }

    @Override
    public void setBalance(double amount) {
        bankAccount.setBalance(amount);
    }


    public double getMaxOverdraft() {
        return this.maxOverdraft;
    }

    @Override
    public void doOperation(Operation operation) {

    }
}
