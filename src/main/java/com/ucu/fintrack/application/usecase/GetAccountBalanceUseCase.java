package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GetAccountBalanceUseCase {

    private final TransactionRepository transactionRepository;

    public GetAccountBalanceUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal execute(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(accountId);

        return transactions.stream()
                .map(transaction -> {
                    if (transaction.getType() == TransactionType.INCOME) {
                        return transaction.getAmount();
                    } else if (transaction.getType() == TransactionType.EXPENSE) {
                        return transaction.getAmount().negate();
                    } else {
                        return BigDecimal.ZERO;
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
