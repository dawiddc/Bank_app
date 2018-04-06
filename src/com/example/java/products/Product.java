package com.example.java.products;

import java.util.UUID;

public interface Product {
   void manageInterest();
   UUID getId();

    void addMoney(double amount);

    double getBalance();

    void setBalance(double amount);
}
