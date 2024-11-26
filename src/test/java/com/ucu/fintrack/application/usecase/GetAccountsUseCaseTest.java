package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.AccountSummaryOutput;
import com.ucu.fintrack.application.dto.GetAccountsOutput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.User;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAccountsUseCaseTest {

    private BankAccountRepository bankAccountRepository;
    private GetAccountsUseCase getAccountsUseCase;

    @BeforeEach
    void setUp() {
        bankAccountRepository = Mockito.mock(BankAccountRepository.class);
        getAccountsUseCase = new GetAccountsUseCase(bankAccountRepository);
    }

    @Test
    void shouldReturnListOfAccountSummaries() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");

        List<BankAccount> accounts = Arrays.asList(
                new BankAccount(1L, "123456", null, Currency.USD, 1000.0),
                new BankAccount(2L, "654321", null, Currency.UYU, 2000.0)
        );

        accounts.get(0).setUser(user);
        accounts.get(0).setNameAccount("Test Account 1");

        when(bankAccountRepository.findAll()).thenReturn(accounts);

        // Act
        GetAccountsOutput result = getAccountsUseCase.execute();

        // Assert
        assertEquals(2, result.getAccounts().size());
        assertEquals("Test Account 1", result.getAccounts().get(0).getNameAccount());
        assertEquals("123456", result.getAccounts().get(0).getIdAccount());
        assertEquals("USD", result.getAccounts().get(0).getCurrency());
        assertEquals("testUser", result.getAccounts().get(0).getUsername());

        verify(bankAccountRepository, times(1)).findAll();
    }
}
