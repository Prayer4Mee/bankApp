package com.bankapp.bankapplication.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankapp.bankapplication.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class TransactionDto {
    
    private String reference;

    private TransactionType type;

    private String accountNumber;
    private String referenceAccountNumber;

    private BigDecimal amount;
    private BigDecimal balanceAfter;

    private LocalDateTime transactionDate;
    
}
