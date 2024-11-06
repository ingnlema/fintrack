package com.ucu.fintrack.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class IncomeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "income_source_id")
    private IncomeSource incomeSource;

    private BigDecimal amount;

    private LocalDate date;
}
