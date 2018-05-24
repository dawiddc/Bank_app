package com.example.java.reporting;

import com.example.java.products.*;

public interface Visitor {

    Product visit(BankAccount bankAccount);

    Product visit(Credit credit);

    Product visit(Deposit deposit);

    Product visit(OverdraftBankAccountDecorator overdraftBankAccountDecorator);

}
