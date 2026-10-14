package com.bankapp.bankapplication.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateAccountRequest {

    private String accountNumber;
    private String name;
    private BigDecimal initialDeposit;
    
}
