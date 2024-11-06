package com.ucu.fintrack.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;  // Nueva relación para clasificar el gasto
}
