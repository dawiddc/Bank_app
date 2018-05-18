package com.example.java.products;

import com.example.java.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OverdraftBankAccountDecoratorTest {
    Bank bank = null;
    BankAccount bankAccount = null;
    OverdraftBankAccountDecorator obad = null;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBankAccount();
        bankAccount = (BankAccount) bank.getBankProducts().get(0);
        obad = new OverdraftBankAccountDecorator(bankAccount, 100);
    }

    @AfterEach
    void tearDown() {
        bank = null;
        bankAccount = null;
        obad = null;
    }

    @Test
    void subtractMoney() {
        obad.subtractMoney(100);
        assertEquals(-100, obad.getBalance());
    }

    @Test
    void hasEnoughMoney() {
        assertFalse(obad.hasEnoughMoney(200));
    }

}