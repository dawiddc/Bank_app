package com.example.java.reporting;

import com.example.java.Bank;
import com.example.java.products.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Over1000ReportTest {

    Bank bank = null;
    BankAccount bankAccount1 = null;
    BankAccount bankAccount2 = null;
    OverdraftBankAccountDecorator obad = null;
    Credit credit = null;
    Deposit deposit = null;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBankAccount();
        bank.createBankAccount();
        bankAccount1 = (BankAccount) bank.getBankProducts().get(0);
        bankAccount1.addMoney(1100);
        bankAccount2 = (BankAccount) bank.getBankProducts().get(1);
        bankAccount2.addMoney(900);
        bank.assignCredit(2000, bankAccount1.getId());
        bank.assignCredit(997, bankAccount2.getId());
    }

    @AfterEach
    void tearDown() {
        bank = null;
        bankAccount1 = null;
        bankAccount1 = null;
    }

    @Test
    void visitBankAccount() {
        List<Product> reports = bank.createBankHistoryOver1000Report();
        assertEquals(2, reports.size());
    }





}