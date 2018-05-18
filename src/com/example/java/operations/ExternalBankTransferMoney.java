package com.example.java.operations;

import com.example.java.Bank;
import com.example.java.products.Product;

import java.util.UUID;

public class ExternalBankTransferMoney implements Operation {

    private final double amount;
    private final UUID receiverID;
    private final Product product;
    private final Bank externalBank;
    private final MoneyTransferMediator mediator;

    public ExternalBankTransferMoney(double amount, UUID receiverID, Product product, MoneyTransferMediator mediator, Bank externalBank) {
        this.product = product;
        this.amount = amount;
        this.receiverID = receiverID;
        this.externalBank = externalBank;
        this.mediator = mediator;
    }

    @Override
    public void execute() {
        if (product.hasEnoughMoney(amount)) {
            mediator.externalTransfer(amount, externalBank, receiverID);
            product.setBalance(product.getBalance() - amount);
        } else {
            System.out.println("Could not find receiver account");
        }
    }
}
