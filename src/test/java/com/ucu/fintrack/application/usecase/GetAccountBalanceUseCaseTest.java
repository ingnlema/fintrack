package com.ucu.fintrack.application.usecase;

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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAccountBalanceUseCaseTest {

    private TransactionRepository transactionRepository;
    private GetAccountBalanceUseCase getAccountBalanceUseCase;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        getAccountBalanceUseCase = new GetAccountBalanceUseCase(transactionRepository);
    }

    @Test
    void shouldCalculateBalanceCorrectly() {
        Long accountId = 1L;
        BankAccount account = new BankAccount(accountId, "123456", null, Currency.USD, 1000.0);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(null, account, "Account 1", BigDecimal.valueOf(100.0), Currency.USD, TransactionType.INCOME, "Salary", LocalDateTime.now(), null, null),
                new Transaction(null, account, "Account 1", BigDecimal.valueOf(50.0), Currency.USD, TransactionType.EXPENSE, "Groceries", LocalDateTime.now(), null, null)
        );

        when(transactionRepository.findByAccount_Id(accountId)).thenReturn(transactions);

        BigDecimal balance = getAccountBalanceUseCase.execute(accountId);

        assertEquals(BigDecimal.valueOf(50.0), balance);
        verify(transactionRepository, times(1)).findByAccount_Id(accountId);
    }

    @Test
    void shouldReturnZeroWhenNoTransactionsExist() {
        Long accountId = 1L;

        when(transactionRepository.findByAccount_Id(accountId)).thenReturn(Collections.emptyList());

        BigDecimal balance = getAccountBalanceUseCase.execute(accountId);

        assertEquals(BigDecimal.ZERO, balance);
        verify(transactionRepository, times(1)).findByAccount_Id(accountId);
    }
}
