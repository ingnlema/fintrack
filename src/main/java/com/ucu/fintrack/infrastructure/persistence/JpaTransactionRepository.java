package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepository {
    @Override
    List<Transaction> findByNameAccount(String nameAccount);

    @Override
    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Override
    List<Transaction> findByType(TransactionType type);
}
