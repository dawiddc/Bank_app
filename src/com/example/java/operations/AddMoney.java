package com.example.java.operations;

import com.example.java.products.Product;

public class AddMoney implements Operation {

    Product product;
    double amount;

    public AddMoney(double amount, Product product) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public void execute() {
        product.setBalance(product.getBalance() + amount);
    }
}
