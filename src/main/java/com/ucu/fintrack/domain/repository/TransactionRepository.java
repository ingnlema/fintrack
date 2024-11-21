package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    List<Transaction> findByNameAccount(String nameAccount);
    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByType(TransactionType type);
    void deleteById(Long id);
}
