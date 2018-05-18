package com.example.java.products;

import com.example.java.Bank;
import com.example.java.operations.TransferMoney;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        TransferMoney moneyTransfer = new TransferMoney(100, bankAccountReceiver.getId(), bankAccount, bank);
        moneyTransfer.execute();
        assertEquals(100, bankAccountReceiver.getBalance());
    }

    @Test
    void transferMoneySender() {
        bankAccount.setOverdraft(true, 1000);
        TransferMoney moneyTransfer = new TransferMoney(100, bankAccountReceiver.getId(), bankAccount, bank);
        moneyTransfer.execute();
        assertEquals(-100, bankAccount.getBalance());
    }

}