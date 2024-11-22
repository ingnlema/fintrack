package com.ucu.fintrack.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount account;

    private String nameAccount;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String description;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    private String notes;


    public static Transaction createSimpleTransaction(BankAccount account, TransactionType type, String description, double amount, LocalDateTime date) {
        return new Transaction(
                null, account, description, BigDecimal.valueOf(amount),
                account.getCurrency(), type, null, date, null, null
        );
    }

}
