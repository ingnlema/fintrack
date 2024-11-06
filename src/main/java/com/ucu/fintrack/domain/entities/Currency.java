package com.ucu.fintrack.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Currency {
    USD("Dólar"),
    UYU("Peso Uruguayo");

    Currency(String description) {
        this.description = description;
    }
    private final String description;


}
