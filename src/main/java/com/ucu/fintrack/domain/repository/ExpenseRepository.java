package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
