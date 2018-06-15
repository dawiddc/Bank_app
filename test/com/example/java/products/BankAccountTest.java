package com.example.java.products;

import com.example.java.Bank;
import com.example.java.operations.ExternalBankTransferMoney;
import com.example.java.operations.TransferMoney;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankAccountTest {

    BankAccount bankAccount, bank2Account, bankAccountReceiver = null;
    Bank bank = null;
    Bank bank2 = null;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.createBankAccount();
        bank.createBankAccount();
        bankAccount = (BankAccount) bank.getBankProducts().get(0);
        bankAccountReceiver = (BankAccount) bank.getBankProducts().get(1);

        bank2 = new Bank();
        bank2.createBankAccount();
        bank2Account = (BankAccount) bank2.getBankProducts().get(0);
    }

    @AfterEach
    void tearDown() {
        bank = null;
        bank2 = null;
        bankAccount = null;
        bank2Account = null;
        bankAccountReceiver = null;
    }

    @Test
    void subtractMoney() {
        bankAccount.setBalance(100);
        bankAccount.subtractMoney(100);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void addMoney() {
        bankAccount.addMoney(100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void transferMoneyReceiver() {
        bankAccount.setBalance(100);
        TransferMoney transferMoney = new TransferMoney(100, bankAccountReceiver.getId(), bankAccount, bank);
        bankAccountReceiver.doOperation(transferMoney);
        assertEquals(100, bankAccountReceiver.getBalance());
    }

    @Test
    void transferMoneySender() {
        bankAccount.setBalance(100);
        TransferMoney transferMoney = new TransferMoney(100, bankAccountReceiver.getId(), bankAccount, bank);
        bankAccountReceiver.doOperation(transferMoney);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void externalTransferMoneyReceiver() {
        bankAccount.setBalance(100);
        ExternalBankTransferMoney transferMoney = new ExternalBankTransferMoney(100, bank2Account.getId(), bankAccount, bank2);
        bankAccount.doOperation(transferMoney);
        assertEquals(100, bank2Account.getBalance());
    }

    @Test
    void externalTransferMoneySender() {
        bankAccount.setBalance(100);
        ExternalBankTransferMoney transferMoney = new ExternalBankTransferMoney(100, bankAccountReceiver.getId(), bankAccount, bank2);
        bank2Account.doOperation(transferMoney);
        assertEquals(0, bankAccount.getBalance());
    }

}