package com.ucu.fintrack.infrastructure.adapter.web.controller;

import com.ucu.fintrack.application.dto.TransactionInput;
import com.ucu.fintrack.application.usecase.*;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllTransactionsUseCase getAllTransactionsUseCase;

    @MockBean
    private GetFilteredTransactionsUseCase getFilteredTransactionsUseCase;

    @MockBean
    private RegisterTransactionUseCase registerTransactionUseCase;

    @Test
    void shouldReturnAllTransactions() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnFilteredTransactions() throws Exception {
        // Arrange
        Long accountId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);
        LocalDateTime endDate = LocalDateTime.now();

        List<Transaction> transactions = List.of(
                new Transaction(1L, null, "Account 1", BigDecimal.valueOf(100.0), Currency.USD, TransactionType.INCOME, "Salary", LocalDateTime.now(), null, null)
        );

        when(getFilteredTransactionsUseCase.execute(eq(accountId), eq(Optional.of(startDate)), eq(Optional.of(endDate)), eq(Optional.of(TransactionType.INCOME))))
                .thenReturn(transactions);

        // Act & Assert
        mockMvc.perform(get("/api/transactions/filters")
                        .param("idAccount", accountId.toString())
                        .param("fechaDesde", startDate.toString())
                        .param("fechaHasta", endDate.toString())
                        .param("tipo", TransactionType.INCOME.name()))
                .andExpect(status().isOk());

        verify(getFilteredTransactionsUseCase, times(1)).execute(eq(accountId), eq(Optional.of(startDate)), eq(Optional.of(endDate)), eq(Optional.of(TransactionType.INCOME)));
    }

    @Test
    void shouldRegisterTransaction() throws Exception {
        // Arrange
        String validInputJson = """
    {
        "idAccount": "1",
        "nameAccount": "Test Account",
        "amount": 100.0,
        "currency": "USD",
        "description": "Test Transaction",
        "transactionType": "INCOME"
    }
    """;

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.valueOf(100.0));
        transaction.setDescription("Test Transaction");

        when(registerTransactionUseCase.execute(any(TransactionInput.class))).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validInputJson))
                .andExpect(status().isOk());
    }


    @Test
    void shouldReturnErrorWhenRegisteringTransactionWithInvalidData() throws Exception {
        // Arrange
        String invalidInputJson = """
    {
        "idAccount": null,
        "amount": 100.0,
        "description": "Test Transaction",
        "transactionType": "INCOME"
    }
    """;

        // Act & Assert
        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidInputJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleFiltersWithEmptyParameters() throws Exception {
        // Arrange
        Long accountId = 1L;

        when(getFilteredTransactionsUseCase.execute(eq(accountId), any(), any(), any())).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/transactions/filters")
                        .param("idAccount", accountId.toString()))
                .andExpect(status().isOk());
    }
}
