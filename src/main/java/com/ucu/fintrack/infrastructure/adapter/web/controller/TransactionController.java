package com.ucu.fintrack.infrastructure.adapter.web.controller;

import com.ucu.fintrack.application.dto.TransactionInput;
import com.ucu.fintrack.application.usecase.*;
import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.TransactionType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Transaction", description = "Endpoints relacionados con transacciones")
public class TransactionController {

    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final GetFilteredTransactionsUseCase getFilteredTransactionsUseCase;
    private final RegisterTransactionUseCase registerTransactionUseCase;

    public TransactionController(
            GetAllTransactionsUseCase getAllTransactionsUseCase,
            GetFilteredTransactionsUseCase getFilteredTransactionsUseCase,
            RegisterTransactionUseCase registerTransactionUseCase) {
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
        this.getFilteredTransactionsUseCase = getFilteredTransactionsUseCase;
        this.registerTransactionUseCase = registerTransactionUseCase;
    }

    @GetMapping("/transactions")
    @Operation(summary = "Obtiene todas las transacciones registradas")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(getAllTransactionsUseCase.execute());
    }

    @GetMapping("/transactions/filters")
    @Operation(summary = "Obtiene transacciones filtradas por parámetros")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(
            @RequestParam Long idAccount,
            @RequestParam Optional<LocalDateTime> fechaDesde,
            @RequestParam Optional<LocalDateTime> fechaHasta,
            @RequestParam Optional<TransactionType> tipo) {
        return ResponseEntity.ok(getFilteredTransactionsUseCase.execute(idAccount, fechaDesde, fechaHasta, tipo));
    }

    @PostMapping("/transaction")
    @Operation(summary = "Registra una nueva transacción")
    public ResponseEntity<Transaction> registerTransaction(@Valid @RequestBody TransactionInput input) {
        Transaction transaction = registerTransactionUseCase.execute(input);
        return ResponseEntity.ok(transaction);
    }
}
