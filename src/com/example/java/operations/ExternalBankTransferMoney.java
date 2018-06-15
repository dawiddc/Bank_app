package com.example.java.operations;

import com.example.java.Bank;
import com.example.java.products.Product;

import java.util.UUID;

public class ExternalBankTransferMoney implements Operation {

    private final double amount;
    private final UUID receiverID;
    private final Product product;
    private final Bank externalBank;

    public ExternalBankTransferMoney(double amount, UUID receiverID, Product product, Bank externalBank) {
        this.product = product;
        this.amount = amount;
        this.receiverID = receiverID;
        this.externalBank = externalBank;
    }

    @Override
    public void execute() {
        if (product.hasEnoughMoney(amount)) {
            MoneyTransferMediator.externalTransfer(amount, externalBank, receiverID);
            product.setBalance(product.getBalance() - amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }
}
