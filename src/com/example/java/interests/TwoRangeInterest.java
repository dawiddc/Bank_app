package com.example.java.interests;

import com.example.java.products.Product;

public class TwoRangeInterest implements AccountInterestState {
    private double balance;

    @Override
    public void countInterests(Product product) {
        balance = product.getBalance();
        if (balance < 10000)
            product.addMoney(balance * 0.045);
        if (balance >= 10000)
            product.addMoney(balance * 0.045 + (balance - 10000) * 0.035);
    }
}
