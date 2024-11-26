package com.ucu.fintrack.infrastructure.adapter.web.controller;

import com.ucu.fintrack.application.usecase.GetAllTransactionsUseCase;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAllTransactionsUseCaseTest {

    private TransactionRepository transactionRepository;
    private GetAllTransactionsUseCase getAllTransactionsUseCase;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        getAllTransactionsUseCase = new GetAllTransactionsUseCase(transactionRepository);
    }

    @Test
    void shouldReturnAllTransactions() {
        // Arrange
        BankAccount account1 = new BankAccount(1L, "123456", null, Currency.USD, 1000.0);
        BankAccount account2 = new BankAccount(2L, "654321", null, Currency.UYU, 2000.0);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, account1, "Account 1", new BigDecimal(100.0), Currency.USD, TransactionType.INCOME, "Test description 1", LocalDateTime.now(), null, null),
                new Transaction(2L, account2, "Account 2", new BigDecimal(200.0), Currency.UYU, TransactionType.EXPENSE, "Test description 2", LocalDateTime.now(), null, null)
        );

        when(transactionRepository.findAll()).thenReturn(transactions);

        // Act
        List<Transaction> result = getAllTransactionsUseCase.execute();

        // Assert
        assertEquals(2, result.size());
        assertEquals(transactions, result);

        verify(transactionRepository, times(1)).findAll();
    }
}
