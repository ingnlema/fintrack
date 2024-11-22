package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.repository.TransactionRepository;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class GetAllTransactionsUseCase {

    private final TransactionRepository transactionRepository;

    public GetAllTransactionsUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> execute() {
        return transactionRepository.findAll();
    }
}
