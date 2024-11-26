package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GetFilteredTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    public GetFilteredTransactionsUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> execute(Long accountId, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate, Optional<TransactionType> type) {
        if (accountId == null) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }

        if (startDate.isPresent() && endDate.isPresent() && type.isPresent()) {
            return transactionRepository.findByAccount_IdAndDateBetweenAndType(accountId, startDate.get(), endDate.get(), type.get());
        } else if (startDate.isPresent() && endDate.isPresent()) {
            return transactionRepository.findByAccount_IdAndDateBetween(accountId, startDate.get(), endDate.get());
        } else if (type.isPresent()) {
            return transactionRepository.findByAccount_IdAndType(accountId, type.get());
        } else {
            return transactionRepository.findByAccount_Id(accountId);
        }
    }
}
