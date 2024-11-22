package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Expense;
import com.ucu.fintrack.domain.repository.ExpenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long>, ExpenseRepository {

}
