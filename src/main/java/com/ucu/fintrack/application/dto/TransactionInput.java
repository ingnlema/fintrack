package com.ucu.fintrack.application.dto;

import com.ucu.fintrack.domain.entities.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionInput {
    private String idAccount;
    private String nameAccount;
    private BigDecimal amount;
    private String currency;
    private TransactionType type;
    private String description;
    private LocalDateTime date;
    private String category;
    private String notes;
}
