package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    Expense save(Expense expense);
    Optional<Expense> findById(Long id);
    List<Expense> findAll();
    void deleteById(Long id);
}
