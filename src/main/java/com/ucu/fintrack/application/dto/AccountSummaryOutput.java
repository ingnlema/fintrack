package com.ucu.fintrack.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummaryOutput {
    private String idAccount;
    private BigDecimal balance;
    private String currency;
    private String username;
    private String nameAccount;
}
