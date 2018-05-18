package com.example.java.operations;

import com.example.java.Bank;
import com.example.java.products.Product;

import java.util.UUID;

public class TransferMoney implements Operation {


    private final double amount;
    private final UUID receiverID;
    private final Product product;
    private final Bank bank;

    public TransferMoney(double amount, UUID receiverID, Product product, Bank bank) {
        this.product = product;
        this.amount = amount;
        this.receiverID = receiverID;
        this.bank = bank;
    }

    @Override
    public void execute() {
        if (bank.findBankProductByID(receiverID) != null && product.hasEnoughMoney(amount)) {
            product.setBalance(product.getBalance() - amount);
            Product receiver = bank.findBankProductByID(receiverID);
            receiver.addMoney(amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }

}
