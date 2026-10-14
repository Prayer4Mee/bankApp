package com.bankapp.bankapplication.frontend;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bankapp.bankapplication.model.Account;
import com.bankapp.bankapplication.model.Transaction;
import com.bankapp.bankapplication.service.AccountService;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class Main implements CommandLineRunner{

    private final AccountService accountService;
    private final Scanner input = new Scanner(System.in);
    
    @Override 
    public void run(String... args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = input.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1" -> createAccount();
                    case "2" -> balanceInquiry();
                    case "3" -> listAccounts();
                    case "4" -> deposit();
                    case "5" -> withdraw();
                    case "6" -> transfer();
                    case "7" -> transactionHistory();
                    case "0" -> {
                        running = false;
                        System.out.println("Goodbye!");
                    } 
                    default -> System.out.println("Invalid choice. Please try again.");      
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Balance Inquiry");
        System.out.println("3. List Accounts");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Transfer");
        System.out.println("7. Transaction History");
        System.out.println("0. Exit");
        System.out.print("Please enter your choice: ");
    }

    // Create Account
    private void createAccount() {
        System.out.print("Account Number: ");
        String accountNumber = input.nextLine().trim();

        System.out.print("Account Holder Name: ");
        String name = input.nextLine().trim();

        System.out.print("Initial Deposit: ");
        BigDecimal initialDeposit = new BigDecimal(input.nextLine().trim());

        Account account = accountService.createAccount(accountNumber, name, initialDeposit);

        System.out.println();
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Name: " + account.getName());
        System.out.printf("Initial Deposit: ₱%,.2f%n", account.getBalance());
        System.out.println("Account created successfully!");
    }

    private void balanceInquiry() {
        System.out.print("Account Number: ");
        String accountNumber = input.nextLine().trim();

        Account account = accountService.getByAccountNumber(accountNumber);

        System.out.println();
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getName());
        System.out.printf("Current Balance: ₱%,.2f%n", account.getBalance());
    }

    private void listAccounts() {
        List<Account> accounts = accountService.listAccounts();

        System.out.println();
        for (Account account : accounts) {
            System.out.printf("%s %s ₱%,.2f%n",
                    account.getAccountNumber(), account.getName(), account.getBalance());
        }
        System.out.println("Total Accounts: " + accounts.size());
    }

    private void deposit() {
        System.out.print("Account Number: ");
        String accountNumber = input.nextLine().trim();

        Account before = accountService.getByAccountNumber(accountNumber);
        BigDecimal previousBalance = before.getBalance();

        System.out.print("Deposit Amount: ");
        BigDecimal amount = new BigDecimal(input.nextLine().trim());

        Account after = accountService.deposit(accountNumber, amount);

        System.out.println();
        System.out.printf("Previous Balance: ₱%,.2f%n", previousBalance);
        System.out.printf("Deposit Amount: ₱%,.2f%n", amount);
        System.out.printf("New Balance: ₱%,.2f%n", after.getBalance());
        System.out.println("Deposit successful!");
    }

    private void withdraw() {
        System.out.print("Account Number: ");
        String accountNumber = input.nextLine().trim();

        Account before = accountService.getByAccountNumber(accountNumber);
        BigDecimal previousBalance = before.getBalance();

        System.out.print("Withdrawal Amount: ");
        BigDecimal amount = new BigDecimal(input.nextLine().trim());

        Account after = accountService.withdraw(accountNumber, amount);

        System.out.println();
        System.out.printf("Previous Balance: ₱%,.2f%n", previousBalance);
        System.out.printf("Withdrawal Amount: ₱%,.2f%n", amount);
        System.out.printf("New Balance: ₱%,.2f%n", after.getBalance());
        System.out.println("Withdrawal successful!");
    }

    private void transfer() {
        System.out.print("Source Account Number: ");
        String fromAccountNumber = input.nextLine().trim();

        System.out.print("Destination Account Number: ");
        String toAccountNumber = input.nextLine().trim();

        System.out.print("Transfer Amount: ");
        BigDecimal amount = new BigDecimal(input.nextLine().trim());

        accountService.transfer(fromAccountNumber, toAccountNumber, amount);

        System.out.println();
        System.out.println("From Account: " + fromAccountNumber);
        System.out.println("To Account: " + toAccountNumber);
        System.out.printf("Amount: ₱%,.2f%n", amount);
        System.out.println("Transfer successful!");
    }

    private void transactionHistory() {
        System.out.print("Account Number: ");
        String accountNumber = input.nextLine().trim();

        List<Transaction> transactions = accountService.getTransactionHistory(accountNumber);

        System.out.println();
        for (Transaction t : transactions) {
            System.out.printf("%s %s ₱%,.2f ₱%,.2f%n",
                    t.getReference(), t.getType(), t.getAmount(), t.getBalanceAfter());
        }
    }
}
