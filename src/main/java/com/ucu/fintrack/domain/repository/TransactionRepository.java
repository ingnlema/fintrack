package com.ucu.fintrack.domain.repository;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    List<Transaction> findByAccount_Id(Long accountId);
    List<Transaction> findByAccount_IdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByAccount_IdAndType(Long accountId, TransactionType type);
    List<Transaction> findByAccount_IdAndDateBetweenAndType(Long accountId, LocalDateTime startDate, LocalDateTime endDate, TransactionType type);
}
