package com.example.java.reporting;

import com.example.java.products.*;

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
        if (credit.getBalance() < 1000) {
            return credit;
        }
        return null;
    }

    @Override
    public Product visit(Deposit deposit) {
        if (deposit.getBalance() > 1000) {
            return deposit;
        }
        return null;
    }

    @Override
    public Product visit(OverdraftBankAccountDecorator overdraftBankAccountDecorator) {
        if (overdraftBankAccountDecorator.getBalance() > 1000) {
            return overdraftBankAccountDecorator;
        }
        return null;
    }
}
