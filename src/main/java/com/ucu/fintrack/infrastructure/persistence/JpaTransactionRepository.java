package com.ucu.fintrack.infrastructure.persistence;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepository {

    @Override
    Transaction save(Transaction transaction);

    @Override
    Optional<Transaction> findById(Long id);

    @Override
    List<Transaction> findAll();

    @Override
    List<Transaction> findByNameAccount(String nameAccount);

    @Override
    List<Transaction> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Override
    List<Transaction> findByType(TransactionType type);

    @Override
    void deleteById(Long id);
}
