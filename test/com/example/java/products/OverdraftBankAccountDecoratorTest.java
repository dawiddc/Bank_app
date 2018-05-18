package com.example.java.products;

import com.example.java.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OverdraftBankAccountDecoratorTest {
    @BeforeEach
    void setUp() {
        Bank bank = new Bank();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void subtractMoney() {
    }

    @Test
    void hasEnoughMoney() {
    }

}