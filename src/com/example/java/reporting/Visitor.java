package com.example.java.reporting;

import com.example.java.products.BankAccount;
import com.example.java.products.Credit;
import com.example.java.products.Product;

public interface Visitor {

    Product visit(BankAccount bankAccount);

    Product visit(Credit credit);

}
