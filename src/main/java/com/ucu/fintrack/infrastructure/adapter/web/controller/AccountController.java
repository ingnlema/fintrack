package com.ucu.fintrack.infrastructure.adapter.web.controller;

import com.ucu.fintrack.application.dto.*;
import com.ucu.fintrack.application.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@Tag(name = "Account", description = "Endpoints relacionados con cuentas bancarias")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountsUseCase getAccountsUseCase;
    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

    public AccountController(
            CreateAccountUseCase createAccountUseCase,
            DeleteAccountUseCase deleteAccountUseCase,
            GetAccountUseCase getAccountUseCase,
            GetAccountsUseCase getAccountsUseCase,
            GetAccountBalanceUseCase getAccountBalanceUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.getAccountsUseCase = getAccountsUseCase;
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
    }

    @PostMapping("/account")
    @Operation(summary = "Crea una nueva cuenta bancaria")
    public ResponseEntity<CreateAccountOutput> createAccount(@RequestBody CreateAccountInput input) {
        return ResponseEntity.ok(createAccountUseCase.execute(input));
    }

    @DeleteMapping("/account/{id}")
    @Operation(summary = "Elimina una cuenta bancaria por ID")
    public ResponseEntity<DeleteAccountOutput> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok(deleteAccountUseCase.execute(id));
    }

    @GetMapping("/account/{id}")
    @Operation(summary = "Obtiene los detalles de una cuenta por ID")
    public ResponseEntity<GetAccountOutput> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(getAccountUseCase.execute(id));
    }

    @GetMapping("/accounts")
    @Operation(summary = "Obtiene una lista de todas las cuentas bancarias")
    public ResponseEntity<GetAccountsOutput> getAccounts() {
        return ResponseEntity.ok(getAccountsUseCase.execute());
    }

    @GetMapping("/account/{id}/balance")
    @Operation(summary = "Obtiene el balance actual de una cuenta por ID")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long id) {
        return ResponseEntity.ok(getAccountBalanceUseCase.execute(id));
    }
}

