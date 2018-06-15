package com.example.java;

import com.example.java.interests.LinearInterest;
import com.example.java.operations.Operation;
import com.example.java.products.BankAccount;
import com.example.java.products.Credit;
import com.example.java.products.Product;
import com.example.java.reporting.Over1000Report;
import com.example.java.reporting.PassAllReport;
import com.example.java.reporting.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    /* Declarations */
    private final ArrayList<Product> bankProducts = new ArrayList<>();
    private final ArrayList<Operation> bankHistory = new ArrayList<>();
    private final UUID bankId = UUID.randomUUID();

    /* Getters and setters */

    public ArrayList<Operation> getBankHistory() {
        return bankHistory;
    }
    public ArrayList<Product> getBankProducts() {return bankProducts; }
    public UUID getBankId() { return bankId; }

    /* Methods */
    public void createBankAccount() {
        UUID owner = UUID.randomUUID();
        bankProducts.add(new BankAccount(this, owner, 0, new LinearInterest()));
    }

    public void assignCredit(double moneyAmount, UUID ownerId) {
        bankProducts.add(new Credit(moneyAmount, this, ownerId, new LinearInterest()));
    }

    public void removeBankProduct(UUID toBeRemovedId) {
        Product productToBeRemoved = findBankProductByID(toBeRemovedId);
        if (productToBeRemoved != null)
            bankProducts.remove(productToBeRemoved);
    }

    public Product findBankProductByID(UUID id) {
        for (Product product : bankProducts)
            if (product.getId().equals(id))
                return product;
        return null;
    }

    public ArrayList<Product> createBankHistoryOver1000Report() {
        ArrayList<Product> result;
        result = buildHistoryReport(new Over1000Report());
        return result;
    }

    public ArrayList<Product> createBankHistoryAllReport() {
        ArrayList<Product> result;
        result = buildHistoryReport(new PassAllReport());
        return result;
    }

    private ArrayList<Product> buildHistoryReport(Visitor visitor) {
        ArrayList<Product> report = new ArrayList<>();
        Product thisProduct;
        for (Product product : bankProducts){
            if ((thisProduct = product.accept(visitor)) != null)
                report.add(thisProduct);
        }
        return report;
    }


}
