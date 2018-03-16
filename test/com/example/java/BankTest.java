package com.example.java;

import com.example.java.Bank;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class BankTest {
    Bank bank;

    @BeforeAll
    void setUp() {
        bank = new Bank();
    }

    @org.junit.jupiter.api.Test
    void createBankAccount1() {

    }

    @AfterAll
    void destroy() {

    }

}
