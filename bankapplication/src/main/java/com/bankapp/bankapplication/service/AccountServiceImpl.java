package com.bankapp.bankapplication.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bankapp.bankapplication.enums.TransactionType;
import com.bankapp.bankapplication.exception.AccountNotFoundException;
import com.bankapp.bankapplication.exception.DuplicateAccountException;
import com.bankapp.bankapplication.exception.InsufficientBalanceException;
import com.bankapp.bankapplication.exception.InvalidAmountException;
import com.bankapp.bankapplication.exception.SameAccountTransferException;
import com.bankapp.bankapplication.model.Account;
import com.bankapp.bankapplication.model.Transaction;
import com.bankapp.bankapplication.repository.AccountRepository;
import com.bankapp.bankapplication.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // Create Account
    @Override
    public Account createAccount(
        String accountNumber, 
        String name, BigDecimal 
        initialDeposit)
        {
            if (accountRepository.existsByAccountNumber(accountNumber)) {
                throw new DuplicateAccountException(
                    "Account number already exists: " + accountNumber);
            }
            if (name == null || name.isBlank()) {
                throw new InvalidAmountException(
                    "Account name must not be empty!"
                );
            }
            if (initialDeposit.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidAmountException(
                    "Initial Deposit must be not zero or greater"
                );
            }
            

            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setName(name);
            account.setBalance(initialDeposit);

        
        return accountRepository.save(account);

    }

    //Balance Inquiry
    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException(
            "Account not found for Account Number: " + accountNumber));
    }

    // List Acounts
    @Override
    public List<Account> listAccounts(){
        return accountRepository.findAll();
    }

    // Deposit
    @Override
    @Transactional 
    public Account deposit(String accountNumber, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new InvalidAmountException(
                "Deposit must be greater than zero, at least piso"
            );
        }
        Account account = getByAccountNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        recordTransaction(
            TransactionType.DEPOSIT,
            accountNumber,
            null,
            amount,
            account.getBalance()
        );
        return account;
    }

    // Withdraw
    @Override
    @Transactional 
    public Account withdraw(String accountNumber, BigDecimal amount) {
        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                "Widthdrawal must be greater than zero."
            );
        }
        Account account = getByAccountNumber(accountNumber);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(
                "Insufficient Balance! Pag tumagos ka pwede nang mangarap!"
            );

        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        recordTransaction(
            TransactionType.WITHDRAW, 
            accountNumber, 
            null, 
            amount, 
            account.getBalance());

        return account;
    }

    //Transfer
    @Override
    @Transactional 
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new SameAccountTransferException(
                "Source and Destination accounts must be different! Ano yan self love?"
            );
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                "Transfer must be greater than one, sa madaling salita, ang transfer ay nagsisimula sa piso."
            );
        }

        Account from = getByAccountNumber(fromAccountNumber);
        Account to = getByAccountNumber(toAccountNumber);

        System.out.println("From balance: " + from.getBalance());
        System.out.println("Transfer amount: " + amount);
        System.out.println("Comparison: " + from.getBalance().compareTo(amount));
        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(
                "Insufficient Balance. Pag tumagos ka, pwede nang mangarap"
            );
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        recordTransaction(
        TransactionType.TRANSFER,
        fromAccountNumber,
        toAccountNumber,
        amount,
        from.getBalance());
    }

    // Transaction History
    @Override
    public List<Transaction> getTransactionHistory(String accountNumber){
        return transactionRepository.findByAccountNumber(accountNumber);
    }


    private void recordTransaction(TransactionType type, String accountNumber, String referenceAccountNumber,
            BigDecimal amount, BigDecimal balanceAfter) {
        Transaction transaction = new Transaction();
        transaction.setReference("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaction.setType(type);
        transaction.setAccountNumber(accountNumber);
        transaction.setReferenceAccountNumber(referenceAccountNumber);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(balanceAfter);

        transactionRepository.save(transaction);
    }


}