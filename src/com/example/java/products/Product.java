package com.example.java.products;

import com.example.java.operations.Operation;
import com.example.java.reporting.Visitor;

import java.util.UUID;

public interface Product {

    void manageInterest();

    UUID getId();

    void addMoney(double amount);

    void subtractMoney(double amount);

    boolean hasEnoughMoney(double amount);

    double getBalance();

    void setBalance(double amount);

    void doOperation(Operation operation);

    Product accept(Visitor visitor);
}
