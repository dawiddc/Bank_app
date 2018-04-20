package com.example.java.products;

import com.example.java.operations.Operation;

import java.util.UUID;

public interface Product {
    void manageInterest();

    UUID getId();

    void addMoney(double amount);

    void subtractMoney(double amount);

    boolean hasEnoughMoney(double amount);

    double getBalance();

    void setBalance(double amount);

    boolean isOverdraft();

    double getMaxOverdraft();

    void doOperation(Operation operation);
}
