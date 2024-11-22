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
    public List<Transaction> execute(String idAccount, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate, Optional<TransactionType> type) {
        if (idAccount == null || idAccount.isBlank()) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }

        if (startDate.isPresent() && endDate.isPresent() && type.isPresent()) {
            return transactionRepository.findByDateBetween(startDate.get(), endDate.get());
        } else{
            return null;}

    }
}
