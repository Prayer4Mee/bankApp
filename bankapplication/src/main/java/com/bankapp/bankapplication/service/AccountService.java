package com.bankapp.bankapplication.service;

import java.math.BigDecimal;
import java.util.List;

import com.bankapp.bankapplication.model.Account;
import com.bankapp.bankapplication.model.Transaction;

public interface AccountService {

    Account createAccount(String accountNumber, String name, BigDecimal initialDeposit);

    Account getByAccountNumber(String accountNumber);

    List<Account> listAccounts();

    Account deposit(String accountNumber, BigDecimal amount);

    Account withdraw(String accountNumber, BigDecimal amount);

    void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount);

    List<Transaction> getTransactionHistory(String accountNumber);
}