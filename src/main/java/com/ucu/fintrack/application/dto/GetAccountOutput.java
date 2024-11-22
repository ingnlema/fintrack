package com.ucu.fintrack.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetAccountOutput {
    private String idAccount;
    private BigDecimal balance;
    private String currency;
    private String username;
    private String nameAccount;
}
