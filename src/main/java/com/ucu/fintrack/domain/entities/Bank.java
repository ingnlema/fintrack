package com.ucu.fintrack.domain.entities;

import lombok.*;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
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
