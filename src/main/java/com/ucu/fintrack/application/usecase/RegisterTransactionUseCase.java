package com.ucu.fintrack.application.usecase;

import com.ucu.fintrack.application.dto.TransactionInput;
import com.ucu.fintrack.domain.entities.BankAccount;
import com.ucu.fintrack.domain.entities.Category;
import com.ucu.fintrack.domain.entities.Transaction;
import com.ucu.fintrack.domain.entities.Currency;
import com.ucu.fintrack.domain.repository.BankAccountRepository;
import com.ucu.fintrack.domain.repository.CategoryRepository;
import com.ucu.fintrack.domain.repository.TransactionRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class RegisterTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CategoryRepository categoryRepository;

    public RegisterTransactionUseCase(TransactionRepository transactionRepository,
                                      BankAccountRepository bankAccountRepository,
                                      CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.categoryRepository = categoryRepository;
    }

    public Transaction execute(TransactionInput input) {
        if (input.getIdAccount() == null) {
            throw new IllegalArgumentException("El ID de la cuenta es obligatorio.");
        }
        if (input.getAmount() == null || input.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        if (input.getCurrency() == null || input.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("La moneda es obligatoria.");
        }
        // Validar y asignar moneda
        Currency currency;
        try {
            String currencyValue = input.getCurrency().trim().toUpperCase(); // Convertir a mayúsculas
            currency = Currency.valueOf(currencyValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El valor de la moneda es inválido: " + input.getCurrency());
        }
        if (input.getTransactionType() == null) {
            throw new IllegalArgumentException("El tipo de transacción es obligatorio.");
        }

        // Buscar cuenta
        BankAccount account = bankAccountRepository.findById(input.getIdAccount())
                .orElseThrow(() -> new IllegalArgumentException("La cuenta especificada no existe."));

        // Buscar categoría (si aplica)
        Category category = null;
        if (input.getCategory() != null && !input.getCategory().trim().isEmpty()) {
            category = categoryRepository.findById(Long.parseLong(input.getCategory()))
                    .orElse(null);
        }

        // Crear y guardar transacción
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setNameAccount(input.getNameAccount());
        transaction.setAmount(input.getAmount());
        transaction.setCurrency(currency);
        transaction.setType(input.getTransactionType());
        transaction.setDescription(input.getDescription());
        transaction.setDate(input.getDate());
        transaction.setCategory(category);
        transaction.setNotes(input.getNotes());

        return transactionRepository.save(transaction);
    }

}

