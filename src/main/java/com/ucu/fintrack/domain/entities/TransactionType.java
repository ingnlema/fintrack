package com.ucu.fintrack.domain.entities;

import lombok.Getter;

@Getter
public enum TransactionType {
    INCOME("entrada"),
    EXPENSE("salida");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
