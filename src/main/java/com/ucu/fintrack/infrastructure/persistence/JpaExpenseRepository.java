package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Expense;
import com.ucu.fintrack.domain.repository.ExpenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long>, ExpenseRepository {

    @Override
    Expense save(Expense expense);

    @Override
    Optional<Expense> findById(Long id);

    @Override
    List<Expense> findAll();

    @Override
    void deleteById(Long id);
}
