package com.example.java.interests;

import com.example.java.AccountInterestState;
import com.example.java.products.BankAccount;

public class C implements AccountInterestState {
    private double balance;

    @Override
    public void countInterests(BankAccount bankAccount) {
        {
            balance = bankAccount.getBalance();
            if (balance < 5000)
                bankAccount.addMoney(balance * 0.05);
            if (balance >= 5000 && balance < 20000)
                bankAccount.addMoney(balance * 0.03);
            if (balance >= 20000)
                bankAccount.addMoney(balance * 0.02);
        }
    }
}
