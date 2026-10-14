package com.bankapp.bankapplication.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferRequest {
    
    private String toAccountNumber;
    private BigDecimal amount;
    
}
