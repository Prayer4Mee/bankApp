package com.bankapp.bankapplication.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapplication.model.Account;
import com.bankapp.bankapplication.model.Transaction;
import com.bankapp.bankapplication.model.dto.request.AmountRequest;
import com.bankapp.bankapplication.model.dto.request.CreateAccountRequest;
import com.bankapp.bankapplication.model.dto.request.TransferRequest;
import com.bankapp.bankapplication.model.dto.response.AccountDto;
import com.bankapp.bankapplication.model.dto.response.TransactionDto;
import com.bankapp.bankapplication.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/accounts")
@RequiredArgsConstructor 
public class AccountController {
    
    private final AccountService accountService;

    // Create Account
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create (@Valid @RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(
            request.getAccountNumber(), 
            request.getName(), 
            request.getInitialDeposit()
        );

        return toDto(account);
    }

    // Balance Inquiry
    @GetMapping("/{accountNumber}")
    public AccountDto getByAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getByAccountNumber(accountNumber);
        return toDto(account);
    }

    //List Accounts
    // http://localhost:8080/api/accounts
    @GetMapping
    public List<AccountDto> listAll() {
        return accountService.listAccounts()
            .stream()
            .map(this::toDto)
            .toList();
    }

    // Deposit
    @PostMapping("/{accountNumber}/deposit")
    public AccountDto deposit(@PathVariable String accountNumber, 
        @Valid @RequestBody AmountRequest request) {
            Account account = accountService.deposit(
                accountNumber, 
                request.getAmount()
            );
            return toDto(account);
    }
    
    // Withdraw
    @PostMapping("/{accountNumber}/withdraw")
    public AccountDto withdraw(@PathVariable String accountNumber,
        @Valid @RequestBody AmountRequest request) {
            Account account = accountService.withdraw(accountNumber, 
                request.getAmount()
            );
            return toDto(account);
    }
    // Transfer
    @PostMapping("/{accountNumber}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@PathVariable String accountNumber, @Valid @RequestBody TransferRequest request) {
        System.out.println("Source Account: " + accountNumber);
        System.out.println("Destination Account: " + request.getToAccountNumber());
        System.out.println("Amount: " + request.getAmount());

        accountService.transfer(accountNumber, 
            request.getToAccountNumber(), 
            request.getAmount()
        );
        
    }
    // Transaction History
    @GetMapping("/{accountNumber}/transactions")
    public List<TransactionDto> getTransactionHistory(@PathVariable String accountNumber) {
        return accountService.getTransactionHistory(accountNumber)
            .stream()
            .map(this::toDto)
            .toList();
    }


    // --- mapping helpers ---

    private AccountDto toDto(Account account) {
        return new AccountDto(
                account.getAccountNumber(),
                account.getName(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }

    private TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getReference(),
                transaction.getType(),
                transaction.getAccountNumber(),
                transaction.getReferenceAccountNumber(),
                transaction.getAmount(),
                transaction.getBalanceAfter(),
                transaction.getTransactionDate()
        );
    }
}
