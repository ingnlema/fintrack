package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.DeleteAccountOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteAccountUseCaseTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private DeleteAccountUseCase deleteAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteAccountSuccessfully() {
        // Arrange
        Long accountId = 1L;
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.of(new BankAccount()));

        // Act
        DeleteAccountOutput output = deleteAccountUseCase.execute(accountId);

        // Assert
        assertNotNull(output);
        assertEquals("Account successfully deleted", output.getMessage());
        assertEquals("1", output.getAccountId());

        verify(bankAccountRepository, times(1)).findById(accountId);
        verify(bankAccountRepository, times(1)).deleteById(accountId);
    }

    @Test
    void shouldThrowExceptionWhenAccountIdIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> deleteAccountUseCase.execute(null));
        assertEquals("El ID de la cuenta es obligatorio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccountDoesNotExist() {
        // Arrange
        Long accountId = 2L;
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> deleteAccountUseCase.execute(accountId));
        assertEquals("La cuenta con ID 2 no existe.", exception.getMessage());

        verify(bankAccountRepository, times(1)).findById(accountId);
        verify(bankAccountRepository, never()).deleteById(accountId);
    }
}
