package com.ucu.fintrack.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class IncomeSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
