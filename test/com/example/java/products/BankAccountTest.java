package com.example.java.products;

import com.example.java.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount, bankAccountReceiver = null;
    Bank bank = null;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBankAccount();
        bank.createBankAccount();
        bankAccount = bank.getBankAccounts().get(0);
        bankAccountReceiver = bank.getBankAccounts().get(1);
    }

    @AfterEach
    void tearDown() {
        bank = null;
        bankAccount = null;
    }

    @Test
    void subtractMoney() {
        bankAccount.setOverdraft(true, 1000);
        bankAccount.subtractMoney(100);
        assertEquals(-100, bankAccount.getBalance());
    }

    @Test
    void addMoney() {
        bankAccount.addMoney(100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void transferMoneyReceiver() {
        bankAccount.setOverdraft(true, 1000);
        bankAccount.transferMoney(100, bankAccountReceiver.getId() );
        assertEquals(100, bankAccountReceiver.getBalance());
    }

    @Test
    void transferMoneySender() {
        bankAccount.setOverdraft(true, 1000);
        bankAccount.transferMoney(100, bankAccountReceiver.getId() );
        assertEquals(-100, bankAccount.getBalance());
    }

}