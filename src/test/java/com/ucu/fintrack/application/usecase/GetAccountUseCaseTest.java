package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.GetAccountOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.User;
import com.ucu.fintrack.domain.repository.BankAccountRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAccountUseCaseTest {

    private final BankAccountRepository bankAccountRepository = Mockito.mock(BankAccountRepository.class);
    private final GetAccountUseCase getAccountUseCase = new GetAccountUseCase(bankAccountRepository);

    @Test
    void shouldReturnAccountDetailsWhenAccountExists() {
        // Arrange
        Long accountId = 1L;
        BankAccount mockAccount = new BankAccount();
        mockAccount.setId(accountId);
        mockAccount.setInitialBalance(1000.0);
        mockAccount.setCurrency(Currency.UYU);
        mockAccount.setNameAccount("Cuenta de Prueba");
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockAccount.setUser(mockUser);

        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.of(mockAccount));

        // Act
        GetAccountOutput result = getAccountUseCase.execute(accountId);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getIdAccount());
        assertEquals(BigDecimal.valueOf(1000.0), result.getBalance());
        assertEquals("UYU", result.getCurrency());
        assertEquals("testuser", result.getUsername());
        assertEquals("Cuenta de Prueba", result.getNameAccount());

        verify(bankAccountRepository, times(1)).findById(accountId);
    }

    @Test
    void shouldThrowExceptionWhenAccountDoesNotExist() {
        // Arrange
        Long accountId = 2L;
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> getAccountUseCase.execute(accountId));
        assertEquals("La cuenta especificada no existe.", exception.getMessage());

        verify(bankAccountRepository, times(1)).findById(accountId);
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> getAccountUseCase.execute(null));
        assertEquals("El ID de la cuenta es obligatorio y debe ser un número positivo.", exception.getMessage());
    }
}
