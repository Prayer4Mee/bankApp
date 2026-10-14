package com.bankapp.bankapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapp.bankapplication.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByAccountNumber(String accountNumber);
    
}
