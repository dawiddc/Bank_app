package com.example.java.operations;

import com.example.java.Bank;
import com.example.java.products.Product;

import java.util.UUID;

/* National Clearing House (KIR) mock */
public class MoneyTransferMediator {

    public static void externalTransfer(double amount, Bank receiverBank, UUID receiverID) {
        if (receiverBank.findBankProductByID(receiverID) != null) {
            Product receiver = receiverBank.findBankProductByID(receiverID);
            receiver.addMoney(amount);
        } else {
            
        }
    }

}
