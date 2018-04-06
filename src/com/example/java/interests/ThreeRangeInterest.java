package com.example.java.interests;

import com.example.java.products.Product;

public class ThreeRangeInterest implements AccountInterestState {
    private double balance;

    @Override
    public void countInterests(Product product) {
        balance = product.getBalance();
        if (balance < 5000)
            product.addMoney(balance * 0.05);
        if (balance >= 5000 && balance < 20000)
            product.addMoney(balance * 0.03);
        if (balance >= 20000)
            product.addMoney(balance * 0.02);
    }
}

