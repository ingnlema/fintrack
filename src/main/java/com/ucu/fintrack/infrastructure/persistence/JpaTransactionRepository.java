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
    List<Transaction> findByAccount_Id(Long accountId);

    @Override
    List<Transaction> findByAccount_IdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    List<Transaction> findByAccount_IdAndType(Long accountId, TransactionType type);

    @Override
    List<Transaction> findByAccount_IdAndDateBetweenAndType(Long accountId, LocalDateTime startDate, LocalDateTime endDate, TransactionType type);
}
