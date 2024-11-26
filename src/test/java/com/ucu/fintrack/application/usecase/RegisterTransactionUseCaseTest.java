package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.TransactionInput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Category;
import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.TransactionType;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import com.ucu.fintrack.domain.repository.CategoryRepository;
import com.ucu.fintrack.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterTransactionUseCaseTest {

    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;
    private CategoryRepository categoryRepository;
    private RegisterTransactionUseCase registerTransactionUseCase;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        bankAccountRepository = Mockito.mock(BankAccountRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        registerTransactionUseCase = new RegisterTransactionUseCase(transactionRepository, bankAccountRepository, categoryRepository);
    }

    @Test
    void shouldRegisterTransactionSuccessfully() {
        // Arrange
        TransactionInput input = new TransactionInput();
        input.setIdAccount(1L);
        input.setAmount(new BigDecimal("100.0"));
        input.setCurrency("USD");
        input.setTransactionType(TransactionType.INCOME);
        input.setDescription("Test Transaction");
        input.setDate(LocalDateTime.now());

        BankAccount account = new BankAccount(1L, "123456", null, Currency.USD, 1000.0);
        Transaction transaction = new Transaction();

        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Act
        Transaction result = registerTransactionUseCase.execute(input);

        // Assert
        assertNotNull(result);
        verify(bankAccountRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void shouldThrowExceptionWhenAccountIdIsInvalid() {
        // Arrange
        TransactionInput input = new TransactionInput();
        input.setIdAccount(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registerTransactionUseCase.execute(input);
        });

        assertEquals("El ID de la cuenta es obligatorio.", exception.getMessage());
        verifyNoInteractions(transactionRepository);
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsInvalid() {
        // Arrange
        TransactionInput input = new TransactionInput();
        input.setIdAccount(1L);
        input.setAmount(new BigDecimal("100.0"));
        input.setCurrency("INVALID");

        BankAccount account = new BankAccount(1L, "123456", null, Currency.USD, 1000.0);
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registerTransactionUseCase.execute(input);
        });

        assertEquals("El valor de la moneda es inválido: INVALID", exception.getMessage());
    }
}
