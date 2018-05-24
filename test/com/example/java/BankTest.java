package com.example.java;

import com.example.java.interests.ThreeRangeInterest;
import com.example.java.products.BankAccount;
import com.example.java.products.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank = null;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @AfterEach
    void tearDown() {
        bank = null;
    }

    @Test
    void removeBankAccount() {
        bank.createBankAccount();
        UUID toBeRemoved = bank.getBankProducts().get(0).getId();
        bank.removeBankProduct(toBeRemoved);
        assertNull(bank.findBankProductByID(toBeRemoved));
    }

    @Test
    void createBankAccount() {
        bank.createBankAccount();
        UUID bankAccountId = bank.getBankProducts().get(0).getId();
        assertNotNull(bank.findBankProductByID(bankAccountId));
    }

    @Test
    void checkAInterestType() {
        bank.createBankAccount();
        BankAccount bankAccount = (BankAccount) bank.getBankProducts().get(0);
        bankAccount.addMoney(100);
        bankAccount.manageInterest();
        assertEquals(104, bankAccount.getBalance());
    }

    @Test
    void checkBInterestType() {
        bank.createBankAccount();
        BankAccount bankAccount = (BankAccount) bank.getBankProducts().get(0);
        bankAccount.addMoney(100);
        bankAccount.setState(new ThreeRangeInterest());
        bankAccount.manageInterest();
        assertEquals(105, bankAccount.getBalance());
        bankAccount.addMoney(20000);
        bankAccount.manageInterest();
        assertEquals(20507.1, bankAccount.getBalance());
    }

    @Test
    void createBankHistoryOver1000Report(){
        for (int i = 0; i < 10; i++){
            bank.createBankAccount();
        }
        for (int i = 0; i < 5; i++){
            bank.getBankProducts().get(i).setBalance(1200);
        }
        ArrayList<Product> reportedProducts = bank.createBankHistoryOver1000Report();
        assertEquals(5, reportedProducts.size());
    }

    @Test
    void createBankHistoryAllReport(){
        for (int i = 0; i < 10; i++){
            bank.createBankAccount();
        }
        for (int i = 0; i < 5; i++){
            bank.getBankProducts().get(i).setBalance(1200);
        }
        ArrayList<Product> reportedProducts = bank.createBankHistoryAllReport();
        assertEquals(10, reportedProducts.size());
    }
}
