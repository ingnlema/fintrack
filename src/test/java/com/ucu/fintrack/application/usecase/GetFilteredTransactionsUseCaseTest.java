package com.ucu.fintrack.application.usecase;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetFilteredTransactionsUseCaseTest {

    private TransactionRepository transactionRepository;
    private GetFilteredTransactionsUseCase getFilteredTransactionsUseCase;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        getFilteredTransactionsUseCase = new GetFilteredTransactionsUseCase(transactionRepository);
    }

    @Test
    void shouldThrowExceptionWhenAccountIdIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                getFilteredTransactionsUseCase.execute(null, Optional.empty(), Optional.empty(), Optional.empty()));
    }

    @Test
    void shouldReturnTransactionsByDateAndType() {
        Long accountId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);
        LocalDateTime endDate = LocalDateTime.now();
        TransactionType type = TransactionType.INCOME;

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, null, "Account 1", BigDecimal.valueOf(100.0), null, type, null, startDate.plusDays(1), null, null)
        );

        when(transactionRepository.findByAccount_IdAndDateBetweenAndType(accountId, startDate, endDate, type)).thenReturn(transactions);

        List<Transaction> result = getFilteredTransactionsUseCase.execute(accountId, Optional.of(startDate), Optional.of(endDate), Optional.of(type));

        assertEquals(1, result.size());
        verify(transactionRepository, times(1)).findByAccount_IdAndDateBetweenAndType(accountId, startDate, endDate, type);
    }

    @Test
    void shouldReturnTransactionsByAccountOnly() {
        Long accountId = 1L;

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, null, "Account 1", BigDecimal.valueOf(100.0), null, null, null, null, null, null)
        );

        when(transactionRepository.findByAccount_Id(accountId)).thenReturn(transactions);

        List<Transaction> result = getFilteredTransactionsUseCase.execute(accountId, Optional.empty(), Optional.empty(), Optional.empty());

        assertEquals(1, result.size());
        verify(transactionRepository, times(1)).findByAccount_Id(accountId);
    }
}
