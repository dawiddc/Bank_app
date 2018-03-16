package com.example.java;

import com.example.java.products.BankAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNull(bank.findBankAccount(toBeRemoved));
    }

    @Test
    void createBankAccount() {
        bank.createBankAccount();
        UUID bankAccountId = bank.getBankAccounts().get(0).getId();
        assertNotNull(bank.findBankAccount(bankAccountId));
    }

    @Test
    void createAccountHistoryReport() {
        bank.createBankAccount();
        BankAccount bankAccount = bank.getBankAccounts().get(0);
        bankAccount.addMoney(100);

        String report = bank.createAccountHistoryReport(bankAccount.getId());
        assertNotNull(report);
    }
}
