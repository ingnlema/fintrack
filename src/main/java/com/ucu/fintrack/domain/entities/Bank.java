package com.ucu.fintrack.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "banks")
public class Bank {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    public Bank(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
