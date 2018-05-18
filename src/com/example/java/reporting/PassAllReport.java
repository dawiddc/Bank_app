package com.example.java.reporting;

import com.example.java.products.BankAccount;
import com.example.java.products.Credit;
import com.example.java.products.Product;

public class PassAllReport implements Visitor {

    @Override
    public Product visit(BankAccount bankAccount) {
        return bankAccount;
    }

    @Override
    public Product visit(Credit credit) {
        return credit;
    }
}
