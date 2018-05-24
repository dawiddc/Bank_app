package com.example.java.reporting;

import com.example.java.products.*;

public class PassAllReport implements Visitor {

    @Override
    public Product visit(BankAccount bankAccount) {
        return bankAccount;
    }

    @Override
    public Product visit(Credit credit) {
        return credit;
    }

    @Override
    public Product visit(Deposit deposit) {
        return deposit;
    }

    @Override
    public Product visit(OverdraftBankAccountDecorator overdraftBankAccountDecorator) {
        return overdraftBankAccountDecorator;
    }
}
