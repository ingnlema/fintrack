package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.CreateAccountInput;
import com.ucu.fintrack.application.dto.CreateAccountOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.User;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import com.ucu.fintrack.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateAccountUseCaseTest {

    private BankAccountRepository bankAccountRepository;
    private UserRepository userRepository;
    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    void setUp() {
        bankAccountRepository = Mockito.mock(BankAccountRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        createAccountUseCase = new CreateAccountUseCase(bankAccountRepository, userRepository);
    }

    @Test
    void shouldCreateAccountSuccessfully() {
        // Arrange
        CreateAccountInput input = new CreateAccountInput();
        input.setInitialBalance(new BigDecimal("100.0"));
        input.setCurrency("USD");
        input.setUsername("testUser");
        input.setNameAccount("Test Account");

        User user = new User();
        user.setUsername("testUser");

        BankAccount savedAccount = new BankAccount();
        savedAccount.setId(1L);
        savedAccount.setInitialBalance(100.0);
        savedAccount.setCurrency(Currency.USD);
        savedAccount.setNameAccount("Test Account");
        savedAccount.setUser(user);

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(savedAccount);

        // Act
        CreateAccountOutput output = createAccountUseCase.execute(input);

        // Assert
        assertEquals(new BigDecimal("100.0"), output.getInitialBalance());
        assertEquals("USD", output.getCurrency());
        assertEquals("testUser", output.getUsername());
        assertEquals("Test Account", output.getNameAccount());

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        CreateAccountInput input = new CreateAccountInput();
        input.setInitialBalance(new BigDecimal("100.0"));
        input.setCurrency("USD");
        input.setUsername("nonexistentUser");
        input.setNameAccount("Test Account");

        when(userRepository.findByUsername("nonexistentUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> createAccountUseCase.execute(input));
        verify(userRepository, times(1)).findByUsername("nonexistentUser");
        verifyNoInteractions(bankAccountRepository);
    }
}
