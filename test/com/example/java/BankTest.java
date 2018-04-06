package com.example.java;

import com.example.java.interests.B;
import com.example.java.products.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        UUID toBeRemoved = bank.getBankAccounts().get(0).getId();
        bank.removeBankAccount(toBeRemoved);
        assertNull(bank.findBankAccountByID(toBeRemoved));
    }

    @Test
    void createBankAccount() {
        bank.createBankAccount();
        UUID bankAccountId = bank.getBankAccounts().get(0).getId();
        assertNotNull(bank.findBankAccountByID(bankAccountId));
    }

    @Test
    void createAccountHistoryReport() {
        bank.createBankAccount();
        BankAccount bankAccount = bank.getBankAccounts().get(0);
        bankAccount.addMoney(100);

        String report = bank.createAccountHistoryReport(bankAccount.getId());
        assertNotNull(report);
    }

    @Test
    void checkAInterestType() {
        bank.createBankAccount();
        BankAccount bankAccount = bank.getBankAccounts().get(0);
        bankAccount.addMoney(100);
        bankAccount.manageInterest();
        assertEquals(104, bankAccount.getBalance());
    }

    @Test
    void checkBInterestType() {
        bank.createBankAccount();
        BankAccount bankAccount = bank.getBankAccounts().get(0);
        bankAccount.addMoney(100);
        bankAccount.setState(new B());
        bankAccount.manageInterest();
        assertEquals(105, bankAccount.getBalance());
        bankAccount.addMoney(20000);
        bankAccount.manageInterest();
        assertEquals(20507.1, bankAccount.getBalance());
    }
}
