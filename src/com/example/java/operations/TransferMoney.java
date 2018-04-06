package com.example.java.operations;

import java.util.UUID;

public class TransferMoney implements Operation {


    private final double amount;
    private final UUID receiverID;

    public TransferMoney(double amount, UUID receiverID) {
        this.amount = amount;
        this.receiverID = receiverID;
    }

    @Override
    public void execute() {

    }
}
