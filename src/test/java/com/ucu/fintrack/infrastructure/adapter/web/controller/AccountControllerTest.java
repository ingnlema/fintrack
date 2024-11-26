package com.ucu.fintrack.infrastructure.adapter.web.controller;

import com.ucu.fintrack.application.dto.*;
import com.ucu.fintrack.application.usecase.*;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    private final CreateAccountUseCase createAccountUseCase = Mockito.mock(CreateAccountUseCase.class);
    private final DeleteAccountUseCase deleteAccountUseCase = Mockito.mock(DeleteAccountUseCase.class);
    private final GetAccountUseCase getAccountUseCase = Mockito.mock(GetAccountUseCase.class);
    private final GetAccountsUseCase getAccountsUseCase = Mockito.mock(GetAccountsUseCase.class);
    private final GetAccountBalanceUseCase getAccountBalanceUseCase = Mockito.mock(GetAccountBalanceUseCase.class);

    private final AccountController accountController = new AccountController(
            createAccountUseCase,
            deleteAccountUseCase,
            getAccountUseCase,
            getAccountsUseCase,
            getAccountBalanceUseCase
    );

    @Test
    void shouldReturnSuccessResponseWhenAccountIsDeleted() {
        // Arrange
        Long accountId = 1L;
        DeleteAccountOutput output = new DeleteAccountOutput();
        output.setMessage("Account successfully deleted");
        output.setAccountId("1");

        when(deleteAccountUseCase.execute(accountId)).thenReturn(output);

        // Act
        ResponseEntity<DeleteAccountOutput> response = accountController.deleteAccount(accountId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());

        verify(deleteAccountUseCase, times(1)).execute(accountId);
    }

    @Test
    void shouldReturnAccountDetailsWhenAccountExists() {
        // Arrange
        Long accountId = 1L;
        GetAccountOutput output = new GetAccountOutput();
        output.setIdAccount("1");
        output.setBalance(new BigDecimal("100.0"));
        output.setCurrency("USD");
        output.setUsername("testUser");
        output.setNameAccount("Test Account");

        when(getAccountUseCase.execute(accountId)).thenReturn(output);

        // Act
        ResponseEntity<GetAccountOutput> response = accountController.getAccount(accountId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());

        verify(getAccountUseCase, times(1)).execute(accountId);
    }

    @Test
    void shouldReturnSuccessResponseWhenAccountIsCreated() {
        // Arrange
        CreateAccountInput input = new CreateAccountInput();
        input.setInitialBalance(new BigDecimal("100.0"));
        input.setCurrency("USD");
        input.setUsername("testUser");
        input.setNameAccount("Test Account");

        CreateAccountOutput output = new CreateAccountOutput();
        output.setInitialBalance(new BigDecimal("100.0"));
        output.setCurrency("USD");
        output.setUsername("testUser");
        output.setNameAccount("Test Account");

        when(createAccountUseCase.execute(input)).thenReturn(output);

        // Act
        ResponseEntity<CreateAccountOutput> response = accountController.createAccount(input);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());

        verify(createAccountUseCase, times(1)).execute(input);
    }

    @Test
    void shouldReturnBalanceForAccount() {
        // Arrange
        Long accountId = 1L;
        BigDecimal balance = new BigDecimal("250.00");

        when(getAccountBalanceUseCase.execute(accountId)).thenReturn(balance);

        // Act
        ResponseEntity<BigDecimal> response = accountController.getAccountBalance(accountId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balance, response.getBody());

        verify(getAccountBalanceUseCase, times(1)).execute(accountId);
    }

    @Test
    void shouldReturnListOfAccountSummaries() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");

        BankAccount account1 = new BankAccount();
        account1.setId(1L);
        account1.setAccountNumber("123456");
        account1.setUser(user);
        account1.setNameAccount("Test Account 1");
        account1.setCurrency(Currency.USD);
        account1.setInitialBalance(1000.0);

        BankAccount account2 = new BankAccount();
        account2.setId(2L);
        account2.setAccountNumber("654321");
        account2.setCurrency(Currency.UYU);
        account2.setInitialBalance(2000.0);

        List<BankAccount> accounts = Arrays.asList(account1, account2);

        GetAccountsOutput output = new GetAccountsOutput();
        output.setAccounts(Arrays.asList(
                new AccountSummaryOutput("1", new BigDecimal("1000.0"), "USD", "testUser", "Test Account 1"),
                new AccountSummaryOutput("2", new BigDecimal("2000.0"), "EUR", null, null)
        ));

        when(getAccountsUseCase.execute()).thenReturn(output);

        // Act
        ResponseEntity<GetAccountsOutput> response = accountController.getAccounts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());

        verify(getAccountsUseCase, times(1)).execute();
    }
}
