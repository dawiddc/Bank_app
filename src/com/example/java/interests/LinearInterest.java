package com.example.java.interests;

import com.example.java.products.Product;

public class LinearInterest implements AccountInterestState {
    @Override
    public void countInterests(Product product) {
        product.addMoney(product.getBalance() * 0.04);
    }
}
