package com.bankapp.bankapplication.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class AccountDto {

    private String accountNumber;
    private String name;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
