package com.example.java.operations;

import com.example.java.Bank;
import com.example.java.products.Product;

import java.util.UUID;

/* National Clearing House (KIR) mock */
public class MoneyTransferMediator {

    public void externalTransfer(double amount, Bank receiverBank, UUID receiverID) {
        if (receiverBank.findBankAccountByID(receiverID) != null) {
            Product receiver = receiverBank.findBankAccountByID(receiverID);
            receiver.addMoney(amount);
        }
    }

}
