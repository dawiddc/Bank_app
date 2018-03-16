package com.example.java.products;

import com.example.java.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount = null;
    BankAccount bankAccount1 =null;
    Bank bank = null;

    @BeforeEach
    void setUp() {

        bank = new Bank();
        bank.createBankAccount();
        bank.createBankAccount();
        bankAccount = bank.getBankAccounts().get(0);
        bankAccount1 = bank.getBankAccounts().get(1);

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

        bankAccount.transferMoney(100, bankAccount1.getId() );

        assertEquals(100, bankAccount1.getBalance());
    }

    @Test
    void transferMoneySender() {
        bankAccount.setOverdraft(true, 1000);
        bankAccount.transferMoney(100, bankAccount1.getId() );
        assertEquals(-100, bankAccount.getBalance());
    }

}