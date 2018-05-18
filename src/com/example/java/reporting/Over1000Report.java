package com.example.java.reporting;

import com.example.java.products.BankAccount;
import com.example.java.products.Credit;
import com.example.java.products.Product;

public class Over1000Report implements Visitor {
    @Override
    public Product visit(BankAccount bankAccount) {
        if (bankAccount.getBalance() > 1000) {
            return bankAccount;
        }
        return null;
    }

    @Override
    public Product visit(Credit credit) {
        if (credit.getBalance() < -1000) {
            return credit;
        }
        return null;
    }
}
