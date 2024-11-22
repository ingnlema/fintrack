package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    Bank save(Bank bank);
    Optional<Bank> findById(Long id);
    List<Bank> findAll();
    void deleteById(Long id);
}
