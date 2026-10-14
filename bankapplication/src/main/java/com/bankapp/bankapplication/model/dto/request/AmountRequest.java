package com.bankapp.bankapplication.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data 
public class AmountRequest {
    
    private BigDecimal amount;
    
}
