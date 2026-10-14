package com.bankapp.bankapplication.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.bankapp.bankapplication.enums.TransactionType;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 

public class Transaction {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // For reference number like Gcash every transaction
    private String reference;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String accountNumber;

    private String referenceAccountNumber;

    private BigDecimal amount;
    private BigDecimal balanceAfter;

    @CreationTimestamp 
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;



    
}
