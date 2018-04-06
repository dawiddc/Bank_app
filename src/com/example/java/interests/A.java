package com.example.java.interests;

import com.example.java.AccountInterestState;
import com.example.java.products.BankAccount;

public class A implements AccountInterestState {
    @Override
    public void countInterests(BankAccount bankAccount) {

        bankAccount.addMoney(bankAccount.getBalance() * 0.04);

    }
}
