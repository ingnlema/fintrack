package com.ucu.fintrack.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountInput {
    private BigDecimal initialBalance;
    private String currency;
    private String username;
    private String nameAccount;
}
