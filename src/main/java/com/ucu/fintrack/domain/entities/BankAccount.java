package com.ucu.fintrack.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    private String nameAccount; // Nombre de la cuenta

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double initialBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount(Long id, String accountNumber, Bank bank, Currency currency, Double initialBalance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.currency = currency;
        this.initialBalance = initialBalance;
    }

}

